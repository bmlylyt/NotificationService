package com.techbow.notification.persist.service;

import com.techbow.notification.data.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NotificationCacheService {

    @Autowired
    private RedisTemplate<Long, Notification> redisTemplate;

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, Notification> hashOperations;

    public void save(Notification notification) {
        hashOperations.put(
                "notification",
                notification.getId(),
                notification
        );
    }

    public Notification findById(Long id) {
        return hashOperations.get("notification", id);
    }

    public void deleteById(Long id) {
        hashOperations.delete("notification", id);
    }
}
