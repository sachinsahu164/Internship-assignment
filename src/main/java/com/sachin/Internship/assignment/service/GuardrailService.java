package com.sachin.Internship.assignment.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GuardrailService {

    private final StringRedisTemplate redis;

    public GuardrailService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    // ✅ Bot limit check (max 100)
    public boolean allowBotReply(Long postId) {

        String key = "post:" + postId + ":bot_count";

        Long count = redis.opsForValue().increment(key);

        if (count == 1) {
            redis.expire(key, Duration.ofHours(1));
        }

        return count <= 100;
    }

    // ✅ Cooldown (10 min)
    public boolean checkCooldown(Long botId, Long userId) {

        String key = "cooldown:bot:" + botId + ":user:" + userId;

        Boolean exists = redis.hasKey(key);

        if (Boolean.TRUE.equals(exists)) {
            return false;
        }

        redis.opsForValue().set(key, "1", Duration.ofMinutes(10));

        return true;
    }

    // ✅ Combined check
    public boolean canBotInteract(Long postId, Long botId, Long userId) {
        return allowBotReply(postId) && checkCooldown(botId, userId);
    }
}