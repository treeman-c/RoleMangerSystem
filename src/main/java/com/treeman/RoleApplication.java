package com.treeman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.treeman.security")
@ComponentScan("com.treeman.service")
public class RoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleApplication.class,args);
    }
}
