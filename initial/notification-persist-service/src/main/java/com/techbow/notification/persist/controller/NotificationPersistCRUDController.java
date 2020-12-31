package com.techbow.notification.persist.controller;

import com.techbow.notification.persist.service.NotificationPersistCRUDService;
import com.techbow.notification.data.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/notification")
public class NotificationPersistCRUDController {

    private static final Logger LOG = Logger.getLogger(NotificationPersistCRUDController.class.getName());

    @Autowired
    private NotificationPersistCRUDService notificationPersistCRUDService;

    @GetMapping
    public List<Notification> getAllNotifications() {
        LOG.info("Get all notifications");
        return notificationPersistCRUDService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notification getNotification(@PathVariable final Long id) {
        LOG.info("Get notification with id" + ":" + id);
        return notificationPersistCRUDService.getNotification(id);
    }

    @PostMapping("")
    public Notification createNotification(@RequestBody Notification notification) {
        LOG.info("Save notification: " + notification);
        return notificationPersistCRUDService.saveNotification(notification);
    }
}
