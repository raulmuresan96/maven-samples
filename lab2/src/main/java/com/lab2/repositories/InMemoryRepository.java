package com.lab2.repositories;

public interface InMemoryRepository <T> {
    void add(T entity);
    boolean contains(T entity);
    void remove(T entity);
    void clear();
}
