package com.techbow.notification.schedule.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationScheduleEventProcessor {
    String PROCESS_CHANNEL = "process";

    @Output(PROCESS_CHANNEL)
    MessageChannel processChannel();
}
