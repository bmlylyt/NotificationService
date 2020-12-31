package com.techbow.notification.render.utils;

import com.techbow.notification.data.Email;
import com.techbow.notification.data.Notification;
import com.techbow.notification.data.User;
import com.techbow.notification.render.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationRenderUtils {
    @Autowired
    private UserService userService;

    public Email renderNotificationAsEmail(Notification notification) {
        Email email = new Email();
        User sender = userService.getUserById(notification.getSenderId());
        User receiver = userService.getUserById(notification.getReceiverId());
        email.setFrom(sender.getEmail());
        email.setTo(receiver.getEmail());
        email.setSubject(notification.getTitle());
        email.setMessage(
                notification.getTitle()
                    + "\n" + notification.getSubtitle()
                    + "\n" + notification.getContent()
        );

        return email;
    }
}
