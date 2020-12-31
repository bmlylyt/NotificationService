package com.techbow.notification.data;

import lombok.Data;

@Data
public class Email implements RenderedContent{
    private String to;
    private String from;
    private String subject;
    private String message;
}
