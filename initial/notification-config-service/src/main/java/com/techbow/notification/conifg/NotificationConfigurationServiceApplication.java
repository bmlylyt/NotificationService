package com.techbow.notification.conifg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class NotificationConfigurationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationConfigurationServiceApplication.class, args);
    }
}
