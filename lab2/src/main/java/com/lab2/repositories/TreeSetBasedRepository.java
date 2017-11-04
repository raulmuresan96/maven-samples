package com.lab2.repositories;

import com.lab2.repositories.InMemoryRepository;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedRepository<T> implements InMemoryRepository<T> {

    private Set<T> entities;

    public TreeSetBasedRepository(){
        entities = new TreeSet<>();
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
