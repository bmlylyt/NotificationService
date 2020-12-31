package com.techbow.notification.persist.dao;

import com.techbow.notification.data.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationMongoDao extends MongoRepository<Notification, Long> {
}
