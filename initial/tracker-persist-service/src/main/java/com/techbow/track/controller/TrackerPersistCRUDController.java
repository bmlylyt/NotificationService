package com.techbow.track.controller;

import com.techbow.notification.data.Tracker;
import com.techbow.track.TrackerService.TrackerPersistService;
import com.techbow.track.TrackerService.TrackerSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tracker")
public class TrackerPersistCRUDController {

    private static final Logger LOG = Logger.getLogger(TrackerPersistCRUDController.class.getName());

    @Autowired
    private TrackerPersistService trackerPersistService;

    @Autowired
    private TrackerSequenceService trackerSequenceService;

    @GetMapping("/{id}")
    public Tracker getTrackerById(@PathVariable Long id) {
        LOG.info("Get Tracker: " + id.toString());
        return trackerPersistService.getTrackerById(id);
    }

    @GetMapping("")
    public Tracker createTracker() {
        Tracker tracker = new Tracker();
        Long trackerId = trackerSequenceService.getNextSequence("tracker_seq");
        System.out.println("Tracker id: " + trackerId);
        tracker.setId(trackerId);
        tracker.setState(Tracker.State.Initialized);
        return trackerPersistService.saveTracker(tracker);
    }

    @GetMapping("/trackers")
    public List<Tracker> getAllTrackers() {
        return trackerPersistService.getAllTrackers();
    }

    @PostMapping("/{id}")
    public Tracker saveTracker(@RequestBody Tracker tracker) {
        LOG.info("Save Tracker: " + tracker.toString());
        if (tracker.getState() == Tracker.State.None) {
            tracker.setId(trackerSequenceService.getNextSequence("tracker_seq"));
            tracker.setState(Tracker.State.Persisted);
        }
        Tracker saved = trackerPersistService.saveTracker(tracker);
        return saved;
    }

    @PutMapping("/{id}")
    public Tracker updateTracker(@PathVariable Long id, @RequestBody Tracker tracker) {
        LOG.info("Update Tracker: " + tracker);
        return trackerPersistService.saveTracker(tracker);
    }

    @DeleteMapping("/{id}")
    public void deleteTracker(@PathVariable @NotNull Long id) {
        LOG.info("Delete tracker: " + id.toString());
        trackerPersistService.deleteTracker(id);
    }

}
