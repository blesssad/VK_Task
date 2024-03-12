package com.example.vk_task.Services.InMemoryCacheServices;

import com.example.vk_task.Entities.Album;
import com.example.vk_task.Interfaces.CacheServiceInterface;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AlbumCacheService implements CacheServiceInterface<Album> {
    private final Map<Long, Album> cache;

    public AlbumCacheService() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(long id, Album value) {
        cache.put(id, value);
    }

    @Override
    public Album get(long id) {
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
