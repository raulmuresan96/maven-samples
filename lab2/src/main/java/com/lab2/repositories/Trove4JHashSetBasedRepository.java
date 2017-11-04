package com.lab2.repositories;

import com.lab2.repositories.InMemoryRepository;
import gnu.trove.set.hash.THashSet;

import java.util.Set;

public class Trove4JHashSetBasedRepository<T> implements InMemoryRepository<T> {
    private Set<T> set;
    public Trove4JHashSetBasedRepository(){
        set = new THashSet<>();
    }

    @Override
    public void add(T entity) {
        set.add(entity);
    }

    @Override
    public boolean contains(T entity) {
        return set.contains(entity);
    }

    @Override
    public void remove(T entity) {
        set.remove(entity);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
