package com.example.vk_task.Interfaces;

public interface CacheServiceInterface<T> {
    void put(long id, T value);

    boolean post(T value);

    T get(long id);

    void delete(long id);

    boolean contains(long id);
}
