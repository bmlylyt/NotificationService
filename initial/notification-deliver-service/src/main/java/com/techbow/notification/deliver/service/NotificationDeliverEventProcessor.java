package com.techbow.notification.deliver.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationDeliverEventProcessor {
    String DELIVER_CHANNEL = "deliver";

    @Input(DELIVER_CHANNEL)
    MessageChannel renderChannel();
}
