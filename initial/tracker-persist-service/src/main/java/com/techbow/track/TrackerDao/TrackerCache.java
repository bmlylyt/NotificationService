package com.techbow.track.TrackerDao;

import com.techbow.notification.data.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TrackerCache {
    @Autowired
    private RedisTemplate<Long, Tracker> redisTemplate;

    @Resource(name = "redisTemplate")
    private HashOperations<String, Long, Tracker> hashOperations;

    public List<Tracker> findAll() {
        return hashOperations.values("tracker_key");
    }

    public void save(Tracker tracker) {
        hashOperations.put("tracker_key", tracker.getId(), tracker);
    }

    public Tracker findById(Long id) {
        return hashOperations.get("tracker_key", id);
    }

    public void deletebyId(Long id) {
        hashOperations.delete("tracker_key", id);
    }
}
