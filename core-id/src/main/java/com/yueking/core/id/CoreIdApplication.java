package com.yueking.core.id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoreIdApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreIdApplication.class, args);
    }
}
