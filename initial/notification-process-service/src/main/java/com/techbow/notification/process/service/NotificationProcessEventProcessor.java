package com.techbow.notification.process.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationProcessEventProcessor {
    String PERSIST_CHANNEL = "persist";
    String PROCESS_CHANNEL = "process";
    String RENDER_CHANNEL = "render";

    @Input(PROCESS_CHANNEL)
    MessageChannel processChannel();

    @Output(PERSIST_CHANNEL)
    MessageChannel persistChannel();

    @Output(RENDER_CHANNEL)
    MessageChannel renderChannel();
}
