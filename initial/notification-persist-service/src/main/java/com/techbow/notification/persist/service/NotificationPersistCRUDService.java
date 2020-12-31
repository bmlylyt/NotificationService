package com.techbow.notification.persist.service;

import com.techbow.notification.data.Notification;
import com.techbow.notification.persist.dao.NotificationMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationPersistCRUDService {

    @Autowired
    private NotificationMongoDao notificationDao;

    @Autowired
    private NotificationSequenceService notificationSequenceService;

    @Autowired
    private NotificationCacheService notificationCacheService;

    public List<Notification> getAllNotifications() {
        return notificationDao.findAll();
    }

    public Notification getNotification(final Long id) {
        Notification notification = notificationCacheService.findById(id);
        if (notification != null) {
            return notification;
        }
        notification = notificationDao.findById(id).orElse(null);
        notificationCacheService.save(notification);
        return notification;
    }

    public Notification saveNotification(Notification notification) {
        if (notification.getId() != null) {
            notificationDao.save(notification);
            return notification;
        }

        notification.setId(notificationSequenceService.getNextSequence("notification_seq"));
        notificationDao.save(notification);
        notificationCacheService.save(notification);
        return notification;
    }

    public void deleteNotification(final Long id) {
        notificationDao.deleteById(id);
        notificationCacheService.deleteById(id);
    }
}
