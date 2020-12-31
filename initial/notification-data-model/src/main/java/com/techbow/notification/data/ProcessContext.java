package com.techbow.notification.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessContext implements Serializable {
    private Tracker tracker;
    private Notification notification;
}
