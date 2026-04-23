package com.sachin.Internship.assignment.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class NotificationScheduler {

    private final StringRedisTemplate redisTemplate;

    public NotificationScheduler(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Runs every 5 minutes to process pending notifications
    @Scheduled(fixedRate = 300000)
    public void processNotifications() {

        // Fetch all users who have pending notifications
        Set<String> keys = redisTemplate.keys("user:*:pending_notifs");

        if (keys == null || keys.isEmpty()) {
            return;
        }

        for (String key : keys) {

            // Get all messages for this user
            List<String> messages = redisTemplate.opsForList().range(key, 0, -1);

            if (messages == null || messages.isEmpty()) {
                continue;
            }

            int totalNotifications = messages.size();

            // Simulating summarized push notification
            System.out.println(
                    "📢 Summarized Notification: " + totalNotifications + " new interactions"
            );

            // Clear processed notifications
            redisTemplate.delete(key);
        }
    }
}
