package com.techbow.notification.persist;


import com.techbow.notification.data.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationPersistApplication {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(NotificationPersistApplication.class, args);
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(environment.getProperty("spring.redis.host"));
        configuration.setPort(
                Integer.parseInt(environment.getProperty("spring.redis.port"))
        );
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<Long, Notification> redisTemplate() {
        RedisTemplate<Long, Notification> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }
}
