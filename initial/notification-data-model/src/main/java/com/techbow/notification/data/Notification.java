package com.techbow.notification.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "notification")
@Data
public class Notification implements Serializable {
    @Id
    private Long id;
    private String priority;
    private String senderId;
    private String receiverId;
    private String title;
    private String subtitle;
    private String content;
}
