package com.techbow.notification.schedule.service;

import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ScheduleNotificationLoader {

    @Autowired
    private TrackerServiceClient trackerServiceClient;

    @Autowired
    private NotificationServiceClient notificationServiceClient;

    @Autowired
    private ScheduledSendTaskQueue sendTaskQueue;

    @Scheduled(fixedRate = 5000)
    public void fetchScheduledNotifications() {
        List<Tracker> trackers =
                trackerServiceClient.fetchScheduledNotificationTrackers(
                        sendTaskQueue.getAvailableQueueSize());
        List<ProcessContext> processContexts =
                notificationServiceClient.getProcessContextForTrackers(trackers);
        processContexts.forEach(
                processContext -> sendTaskQueue.enqueueProcessContext(processContext)
        );
    }
}
