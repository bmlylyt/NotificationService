package com.techbow.notification.schedule.service;

import com.techbow.notification.data.ProcessContext;
import com.techbow.notification.data.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

@Component
@EnableBinding(NotificationScheduleEventProcessor.class)
public class ScheduledSendTaskQueue {

    private static final Integer QUEUE_SIZE =128;

    private static final Logger LOG = Logger.getLogger(ScheduledSendTaskQueue.class.getName());

    private Queue<ProcessContext> sendQueue = new LinkedList<>();

    @Autowired
    private NotificationScheduleEventProcessor notificationScheduleEventProcessor;

    public Integer getAvailableQueueSize() {
        return QUEUE_SIZE - sendQueue.size();
    }

    public boolean enqueueProcessContext(ProcessContext processContext) {
        if (sendQueue.size() >= QUEUE_SIZE) {
            return false;
        }
        sendQueue.offer(processContext);
        return true;
    }

    @Scheduled(fixedRate = 200)
    public void scheduedSend() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        while (sendQueue.size() > 0) {
            ProcessContext context = sendQueue.peek();
            if (context.getScheduledTimestamp().after(currentTimestamp)) {
                Tracker tracker = context.getTracker();
                tracker.setState(Tracker.State.SendPromised);
                notificationScheduleEventProcessor.processChannel().send(MessageBuilder.withPayload(context).build());
                sendQueue.poll();
            } else {
                break;
            }
        }
    }
}
