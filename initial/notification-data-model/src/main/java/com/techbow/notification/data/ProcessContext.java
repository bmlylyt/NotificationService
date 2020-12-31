package com.techbow.notification.data;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ProcessContext implements Serializable {
    private Tracker tracker;
    private Notification notification;
    private RenderedContent renderedContent;
    private Timestamp scheduledTimestamp;
}
