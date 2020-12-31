package com.techbow.notification.process.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationProcessEventProcessor {
    static String PERSIST_CHANNEL = "persist";
    static String PROCESS_CHANNEL = "process";

    @Input(PROCESS_CHANNEL)
    MessageChannel processChannel();

    @Output(PERSIST_CHANNEL)
    MessageChannel persistChannel();
}
