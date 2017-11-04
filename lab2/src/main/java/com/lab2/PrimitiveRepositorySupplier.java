package com.lab2;

import com.lab2.primitiveRepo.FastUtilsLongHashSet;
import com.lab2.primitiveRepo.InMemoryRepositoryPrimitive;
import com.lab2.primitiveRepo.KolobokeLongHashSet;
import com.lab2.primitiveRepo.Trove4JPrimitiveHashSet;

import java.util.function.Supplier;

public enum PrimitiveRepositorySupplier implements Supplier<InMemoryRepositoryPrimitive>{
    KOLOBOKE_HASHSET() {
        @Override
        public InMemoryRepositoryPrimitive get() {
            return new KolobokeLongHashSet();
        }
    },
    TROVE4J_HASHSET() {
        @Override
        public InMemoryRepositoryPrimitive get() {
            return new Trove4JPrimitiveHashSet();
        }
    },
    FASTUTIL_HASHSET() {
        @Override
        public InMemoryRepositoryPrimitive get() {
            return new FastUtilsLongHashSet();
        }
    }

}
