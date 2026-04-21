package com.sachin.Internship.assignment.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class NotificationService {

    private final StringRedisTemplate redis;

    public NotificationService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public void handleNotification(Long userId, String message) {

        String cooldownKey = "notif:cooldown:user:" + userId;
        String listKey = "user:" + userId + ":pending_notifs";

        Boolean exists = redis.hasKey(cooldownKey);

        if (Boolean.TRUE.equals(exists)) {
            // store in list
            redis.opsForList().rightPush(listKey, message);
        } else {
            // send immediately
            System.out.println("🚀 Push Notification Sent: " + message);

            // set cooldown 15 min
            redis.opsForValue().set(cooldownKey, "1", Duration.ofMinutes(15));
        }
    }
}