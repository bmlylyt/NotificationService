package com.techbow.track.TrackerDao;

import com.techbow.notification.data.Tracker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerDao extends MongoRepository<Tracker, Long> {
}
