package com.lab2.repositories;

import com.lab2.repositories.InMemoryRepository;

import java.util.HashSet;
import java.util.Set;

public class HashSetBasedRepository<T> implements InMemoryRepository<T> {
    private Set<T> entities;

    public HashSetBasedRepository(){
        entities = new HashSet<>();
    }

    @Override
    public void add(T entity) {
        entities.add(entity);
    }

    @Override
    public boolean contains(T entity) {
        return entities.contains(entity);
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
