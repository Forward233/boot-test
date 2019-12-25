package com.boot.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: yhl
 * @DateTime: 2019/11/15 16:21
 * @Description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "person")
public class PersonYmlConfig {
    private String name;
    private String gender;
    private Car car;

    @Data
    public static class Car {
        String carName;
        String carColor;
    }

}
