package com.techbow.notification.persist.controller;

import com.techbow.notification.data.Notification;
import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import com.techbow.notification.persist.dao.NotificationPersistEventProcessor;
import com.techbow.notification.persist.service.NotificationPersistCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@EnableBinding(NotificationPersistEventProcessor.class)
public class NotificationPersistEventController {

    private static final Logger LOG = Logger.getLogger(NotificationPersistCRUDController.class.getName());

    @Autowired
    private NotificationPersistCRUDService notificationPersistCRUDService;

    @Autowired
    private NotificationPersistEventProcessor notificationPersistEventProcessor;

    @StreamListener(NotificationPersistEventProcessor.PERSIST_CHANNEL)
    public void processPersistEvent(ProcessContext processContext) {
        LOG.info("Save processContext: " + processContext);
        Notification savedNotification = notificationPersistCRUDService
                .saveNotification(processContext.getNotification());

        processContext.setNotification(savedNotification);
        Tracker tracker = processContext.getTracker();
        tracker.setState(Tracker.State.Persisted);

        processContext.setTracker(tracker);

        notificationPersistEventProcessor.processChannel().send(
                MessageBuilder.withPayload(processContext).build()
        );
        LOG.info("Sent ProcessContext to process: " + processContext);
    }
}
