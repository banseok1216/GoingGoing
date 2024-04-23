package com.example.user;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserCachedReader {
    private ConcurrentHashMap<String, User> cache;
    public UserCachedReader() {
        this.cache = new ConcurrentHashMap<>();
    }
    public void put(String key, User value) {
        cache.put(key, value);
    }

    public User get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }
}
