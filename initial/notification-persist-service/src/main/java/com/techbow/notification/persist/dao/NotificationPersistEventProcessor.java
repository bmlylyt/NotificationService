package com.techbow.notification.persist.dao;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationPersistEventProcessor {
    String PERSIST_CHANNEL = "persist";
    String PROCESS_CHANNEL = "process";

    @Input(PERSIST_CHANNEL)
    MessageChannel persistChannel();

    @Output(PROCESS_CHANNEL)
    MessageChannel processChannel();
}
