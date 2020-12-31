package com.techbow.notification.persist.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification_seq")
@Data
public class NotificationSequence {
    @Id
    private String id;
    private Long seq;
}
