package com.techbow.notification.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationSecheduleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationSecheduleServiceApplication.class, args);
    }
}
