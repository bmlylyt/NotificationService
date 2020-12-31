package com.techbow.notification.schedule.service;

import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceClient {

    public List<ProcessContext> getProcessContextForTrackers(List<Tracker> trackers) {
        // load notification from persist
        // join notification with tracker to form process context
        return new ArrayList<ProcessContext>();
    }
}
