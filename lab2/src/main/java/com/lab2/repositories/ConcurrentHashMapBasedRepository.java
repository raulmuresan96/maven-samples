package com.lab2.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasedRepository<T> implements InMemoryRepository<T> {

    private Map<T, Boolean> entities;

    public ConcurrentHashMapBasedRepository() {
        entities = new ConcurrentHashMap<>();
    }

    @Override
    public void add(T entity) {
        entities.put(entity, true);
    }

    @Override
    public boolean contains(T entity) {
        return entities.containsKey(entity);
    }

    @Override
    public void remove(T entity) {
        entities.remove(entity);
    }

    @Override
    public void clear() {
        entities.clear();
    }
}
