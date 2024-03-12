package com.example.vk_task.Services.InMemoryCacheServices;

import com.example.vk_task.Entities.User;
import com.example.vk_task.Interfaces.CacheServiceInterface;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCacheService implements CacheServiceInterface<User> {
    private final Map<Long, User> cache;

    public UserCacheService() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(long id, User value) {
        cache.put(id, value);
    }

    @Override
    public User get(long id) {
        return cache.getOrDefault(id, null);
    }

    @Override
    public void delete(long id) {
        cache.remove(id);
    }

    @Override
    public boolean contains(long id) {
        return cache.containsKey(id);
    }
}