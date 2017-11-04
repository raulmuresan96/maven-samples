package com.lab2.repositories;

import com.lab2.repositories.InMemoryRepository;
import org.eclipse.collections.impl.factory.Lists;

import java.util.List;

public class MutableListBasedRepository<T> implements InMemoryRepository<T> {

    private List<T> entities;

    public MutableListBasedRepository(){
        entities = Lists.mutable.empty();
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
