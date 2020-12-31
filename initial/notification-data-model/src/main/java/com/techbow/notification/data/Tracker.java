package com.techbow.notification.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "tracker")
@Data
public class Tracker implements Serializable {
    public enum State {
        None,
        Initialized,
        Persisted,
        Rendered,
        Delivered,
        Error,
        SendPromised,
        Scheduled
    }

    @Id
    private Long id;
    private Long notificationId;
    private State state;
}
