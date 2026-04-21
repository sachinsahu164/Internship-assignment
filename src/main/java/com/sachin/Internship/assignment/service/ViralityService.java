package com.sachin.Internship.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViralityService {

    @Autowired
    private StringRedisTemplate redis;

    public void incrementScore(Long postId, String type) {

        String key = "post:" + postId + ":virality_score";

        int points = switch (type) {
            case "BOT_REPLY" -> 1;
            case "LIKE" -> 20;
            case "COMMENT" -> 50;
            default -> 0;
        };

        redis.opsForValue().increment(key, points);
    }

    public String getScore(Long postId) {
        String key = "post:" + postId + ":virality_score";
        return redis.opsForValue().get(key);
    }
}