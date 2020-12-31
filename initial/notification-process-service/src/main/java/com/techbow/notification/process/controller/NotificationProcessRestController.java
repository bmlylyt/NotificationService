package com.techbow.notification.process.controller;

import com.techbow.notification.data.Notification;
import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import com.techbow.notification.process.service.NotificationProcessEventProcessor;
import com.techbow.notification.process.service.TrackerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@RestController
@RequestMapping("/notification")
@EnableBinding(NotificationProcessEventProcessor.class)
public class NotificationProcessRestController {

    private static final Logger LOG = Logger.getLogger(NotificationProcessRestController.class.getName());

    private static final String URL = "http://localhost:8080/persist/notification/";

    @Autowired
    private TrackerServiceClient trackerServiceClient;

    @Autowired
    private NotificationProcessEventProcessor notificationProcessEventProcessor;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public String getAllNotifications() {
        return "Process service";
    }

    @GetMapping("/{id}")
    public Notification getAllNotifications(@PathVariable Long id) {
        System.out.println(id);
        return restTemplate.getForObject(URL + id, Notification.class);
    }

    @PostMapping("")
    public String processNotification(@RequestBody @NotNull Notification notification) {
        LOG.info("Received notification request: " + notification.toString());

        // Generate tracker
        Tracker tracker = trackerServiceClient.createTracker();
        tracker.setId(notification.getId());
        LOG.info("Created notification tracker: " + tracker);

        // Save notification
        ProcessContext processContext = new ProcessContext();
        processContext.setTracker(tracker);
        processContext.setNotification(notification);

        notificationProcessEventProcessor.persistChannel().send(
                MessageBuilder.withPayload(processContext).build()
        );

        LOG.info("Sent notification for persist");
        // Render notification

        return "Processed";
    }
}
