package com.example.user.implementation;

import com.example.user.external.ExternalUserSender;
import com.example.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserCachedHandler {
    private final ConcurrentHashMap<String, User> cache;
    public UserCachedHandler() {
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
