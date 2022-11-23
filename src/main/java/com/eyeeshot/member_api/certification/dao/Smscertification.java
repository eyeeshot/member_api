package com.eyeeshot.member_api.certification.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class Smscertification {
    private final RedisTemplate<String,String> redisTemplate;

    private final String SMS_KEY_PREFIX = "sms:";

    public void createSmsCertification(String key, String value, int time) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(SMS_KEY_PREFIX + key, value, Duration.ofSeconds(time));
    }

    public String getSmsCertification(String key) {
        return redisTemplate.opsForValue().get(SMS_KEY_PREFIX + key);
    }

    public void deleteSmsCertification(String key) {
        redisTemplate.delete(SMS_KEY_PREFIX + key);
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(SMS_KEY_PREFIX + key));
    }
}
