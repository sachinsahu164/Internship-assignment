package com.sachin.Internship.assignment.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class NotificationScheduler {

    private final StringRedisTemplate redis;

    public NotificationScheduler(StringRedisTemplate redis) {
        this.redis = redis;
    }

    // every 5 min
    @Scheduled(fixedRate = 300000)
    public void processNotifications() {

        Set<String> keys = redis.keys("user:*:pending_notifs");

        if (keys == null) return;

        for (String key : keys) {

            List<String> messages = redis.opsForList().range(key, 0, -1);

            if (messages == null || messages.isEmpty()) continue;

            int count = messages.size();

            System.out.println("📢 Summarized Notification: " + count + " new interactions");

            redis.delete(key);
        }
    }
}