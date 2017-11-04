package com.lab2;

import com.lab2.repositories.*;

import java.util.function.Supplier;

public enum RepositorySupplier implements Supplier<InMemoryRepository<Order>> {
    ARRAY_LIST() {
        @Override
        public InMemoryRepository<Order> get() {
            return new ArrayListBasedRepository<>();
        }
    },
    HASH_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new HashSetBasedRepository<>();
        }
    },
    TREE_SET() {
        @Override
        public InMemoryRepository<Order> get() {
            return new TreeSetBasedRepository<>();
        }
    },
    CONCURRENT_HASHMAP() {
        @Override
        public InMemoryRepository<Order> get() {
            return new ConcurrentHashMapBasedRepository<>();
        }
    },
    MUTABLE_LIST() {//Eclipse Collection
        @Override
        public InMemoryRepository<Order> get() {
            return new MutableListBasedRepository<>();
        }
    },
    TROVE_HASHSET() {//Trove4J
        @Override
        public InMemoryRepository<Order> get() {
            return new Trove4JHashSetBasedRepository<>();
        }
    }



}
