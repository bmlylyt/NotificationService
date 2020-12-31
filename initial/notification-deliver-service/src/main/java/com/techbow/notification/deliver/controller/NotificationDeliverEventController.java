package com.techbow.notification.deliver.controller;

import com.techbow.notification.data.*;
import com.techbow.notification.deliver.service.EmailDeliverService;
import com.techbow.notification.deliver.service.NotificationDeliverEventProcessor;
import com.techbow.notification.deliver.service.TrackerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
@EnableBinding(NotificationDeliverEventProcessor.class)
public class NotificationDeliverEventController {

    private static final Logger LOG = Logger.getLogger(NotificationDeliverEventController.class.getName());

    @Autowired
    private NotificationDeliverEventProcessor notificationDeliverEventProcessor;

    @Autowired
    private TrackerServiceClient trackerServiceClient;

    @Autowired
    private EmailDeliverService emailDeliverService;

    @StreamListener(NotificationDeliverEventProcessor.DELIVER_CHANNEL)
    public void processEvent(ProcessContext processContext) {
        Tracker tracker = processContext.getTracker();

        Notification notification = processContext.getNotification();

        RenderedContent renderedContent = processContext.getRenderedContent();
        if (renderedContent instanceof Email) {
            emailDeliverService.sendEmail((Email)renderedContent);
        }
        tracker.setState(Tracker.State.Delivered);
        trackerServiceClient.updateTracker(tracker);
        LOG.info("Updated tracker state: " + tracker);

    }
}
