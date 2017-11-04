package com.lab2.repositories;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBasedRepository<T> implements InMemoryRepository<T> {
    private List<T> entities;

    public ArrayListBasedRepository() {
        entities = new ArrayList<>();
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
