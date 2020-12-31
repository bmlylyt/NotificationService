package com.techbow.track.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tracker_seq")
@Data
public class TrackerSequence {
    @Id
    private String id;
    private Long seq;
}
