package com.techbow.track.TrackerService;

import com.techbow.notification.data.Tracker;
import com.techbow.track.TrackerDao.TrackerCache;
import com.techbow.track.TrackerDao.TrackerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackerPersistService {

    @Autowired
    private TrackerDao trackerDao;

    @Autowired
    private TrackerCache trackerCache;

    public List<Tracker> getAllTrackers() {
        List<Tracker> trackers = new ArrayList<>();
        trackerCache.findAll().forEach(trackers::add);
        if (!trackers.isEmpty()) {
            return trackers;
        }
        trackers = trackerDao.findAll();
        trackers.forEach(trackerCache::save);
        return trackerDao.findAll();
    }

    public Tracker getTrackerById(Long id) {
        return trackerDao.findById(id).orElse(null);
    }

    public Tracker saveTracker(Tracker tracker) {
        return trackerDao.save(tracker);
    }

    public void deleteTracker(Long id) {
        trackerDao.deleteById(id);
        trackerCache.deletebyId(id);
    }
}
