package com.github.quanticc.gsheets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class WebfluxGsheetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxGsheetsApplication.class, args);
    }

}
