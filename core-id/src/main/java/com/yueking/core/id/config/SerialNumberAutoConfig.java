package com.yueking.core.id.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SerialNumberAutoConfig {
    @Bean
    @ConfigurationProperties("serial.number")
    public SerialNumberProperties getSerialNumberConfig() {
        return new SerialNumberProperties();
    }
}
