package com.techbow.notification.render.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface NotificationRenderEventProcessor {
    String RENDER_CHANNEL = "render";
    String DELIVER_CHANNEL = "deliver";

    @Input(RENDER_CHANNEL)
    MessageChannel renderChannel();

    @Output(DELIVER_CHANNEL)
    MessageChannel deliverChannel();
}
