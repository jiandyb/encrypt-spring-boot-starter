package com.jiandyb.properties;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @BelongsProject: encrypt-spring-boot-starter
 * @BelongsPackage: com.jiandyb.properties
 * @Author: JianDyb
 * @CreateTime: 2023-03-28  09:56
 * @Description: TODO
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    private final static String DEF_KEY = "1234567890123456";

    private String key = DEF_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
