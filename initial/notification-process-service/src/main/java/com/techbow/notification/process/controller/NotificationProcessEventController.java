package com.techbow.notification.process.controller;

import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import com.techbow.notification.process.service.NotificationProcessEventProcessor;
import com.techbow.notification.process.service.TrackerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.logging.Logger;

@EnableBinding(NotificationProcessEventProcessor.class)
@EnableDiscoveryClient
public class NotificationProcessEventController {
    private static final Logger LOG = Logger.getLogger(NotificationProcessEventController.class.getName());

    @Autowired
    private NotificationProcessEventProcessor notificationProcessEventProcessor;

    @Autowired
    private TrackerServiceClient trackerServiceClient;

    @StreamListener(NotificationProcessEventProcessor.PROCESS_CHANNEL)
    public void processNotification(ProcessContext processContext) {
        LOG.info("Received ProcessContext: " + processContext);
        Tracker tracker = processContext.getTracker();
        if (tracker.getState() == Tracker.State.Persisted) {
            LOG.info("About to process tracker: " + tracker);
            trackerServiceClient.updateTracker(tracker);
            LOG.info("Updated tracker: " + tracker);
            // Send notification for rendering

        } else {
            LOG.info("Unexpected track state for tracker: " + tracker.toString());
            tracker.setState(Tracker.State.Error);
            trackerServiceClient.updateTracker(tracker);
        }
    }
}
