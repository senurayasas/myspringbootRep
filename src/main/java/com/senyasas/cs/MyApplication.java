package com.senyasas.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
