package com.example.vk_task.Interfaces;

public interface EntityServiceInterface<T> {
    T get(Long id);
    void post(T entity);
    void put(Long id, T entity);
    void delete(Long id);
}
