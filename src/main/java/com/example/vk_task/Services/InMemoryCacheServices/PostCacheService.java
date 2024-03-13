package com.example.vk_task.Services.InMemoryCacheServices;

import com.example.vk_task.Entities.Post;
import com.example.vk_task.Interfaces.CacheServiceInterface;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostCacheService implements CacheServiceInterface<Post> {
    private final Map<Long, Post> cache;

    public PostCacheService() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(long id, Post value) {
        cache.put(id, value);
    }

    @Override
    public boolean post(Post value) {
        if (contains(value.getId()))
            return false;

        cache.put(value.getId(), value);
        return true;
    }

    @Override
    public Post get(long id) {
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
