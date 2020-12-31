package com.techbow.notification.render.controller;

import com.techbow.notification.data.Notification;
import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import com.techbow.notification.render.service.TrackerServiceClient;
import com.techbow.notification.render.service.NotificationRenderEventProcessor;
import com.techbow.notification.render.utils.NotificationRenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@EnableBinding(NotificationRenderEventProcessor.class)
public class NotificationRenderEventController {

    private static final Logger LOG = Logger.getLogger(NotificationRenderEventController.class.getName());

    @Autowired
    private NotificationRenderEventProcessor notificationRenderEventProcessor;

    @Autowired
    private TrackerServiceClient trackerServiceClient;

    @Autowired
    private NotificationRenderUtils notificationRenderUtils;

    @StreamListener(NotificationRenderEventProcessor.RENDER_CHANNEL)
    public void processEvent(ProcessContext processContext) {
        Tracker tracker = processContext.getTracker();

        Notification notification = processContext.getNotification();

        if (notification.getPreferredType() == "email") {
            processContext.setRenderedContent(notificationRenderUtils.
                    renderNotificationAsEmail(notification));
        } else {
            // TODO
        }

        trackerServiceClient.updateTracker(tracker);
        LOG.info("Updated tracker state: " + tracker);

        tracker.setState(Tracker.State.Rendered);

        notificationRenderEventProcessor.deliverChannel().send(
                MessageBuilder.withPayload(processContext).build()
        );
    }
}
