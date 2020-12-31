package com.techbow.notification.schedule.service;

import com.techbow.notification.data.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrackerServiceClient {
    private static final String URL = "http://localhost:8102";

    @Autowired
    private RestTemplate restTemplate;

    public Tracker createTracker() {
        return restTemplate.getForObject(URL + "/tracker", Tracker.class);
    }

    public Tracker getTracker(Long id) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", Long.toString(id));
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).path("/tracker/{id}")
                .buildAndExpand(urlParams);
        return restTemplate.getForObject(
                uriComponents.toUriString(),
                Tracker.class
        );
    }

    public Tracker updateTracker(Tracker tracker) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", Long.toString(tracker.getId()));
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(URL)
                .path("/tracker/{id}").buildAndExpand(urlParams);
        return restTemplate.postForObject(
                uriComponents.toUriString(),
                tracker,
                Tracker.class
        );
    }

    public List<Tracker> fetchScheduledNotificationTrackers(int size) {
        return new ArrayList<>();
    }
}
