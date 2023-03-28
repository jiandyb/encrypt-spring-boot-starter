package com.jiandyb.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiandyb.annotation.Encrypt;
import com.jiandyb.properties.EncryptProperties;
import com.jiandyb.utils.AesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @BelongsProject: encrypt-spring-boot-starter
 * @BelongsPackage: com.jiandyb.handler
 * @Author: JianDyb
 * @CreateTime: 2023-03-28  10:12
 * @Description: TODO
 * @Version: 1.0
 */
@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
public class EncryptResponseHandler implements ResponseBodyAdvice<Object> {
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<?
            extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        byte[] bytes = encryptProperties.getKey().getBytes();
        try {
            if (body != null) {
                body = AesUtils.encrypt(om.writeValueAsString(body).getBytes(), bytes);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return body;
    }
}
