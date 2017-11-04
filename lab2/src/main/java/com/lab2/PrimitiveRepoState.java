package com.lab2;

import com.lab2.primitiveRepo.InMemoryRepositoryPrimitive;
import org.openjdk.jmh.annotations.*;


import java.util.stream.LongStream;

@State(Scope.Benchmark)
public class PrimitiveRepoState {
    @Param
    private PrimitiveRepositorySupplier repositorySupplier;

    public InMemoryRepositoryPrimitive elements;

    //we initialize the InMemoryRepository
    @Setup(Level.Iteration)
    public void setUp(SizeState sizeState) {
        System.out.println(getClass().getSimpleName() + " > setup > populate");
        elements = repositorySupplier.get();

        LongStream.rangeClosed(1, sizeState.size)
                .forEach(elements::add);
    }

    /* code gets executed after every iteration */
    @TearDown(Level.Iteration)
    public void tearDown() {
        System.out.println(getClass().getSimpleName() + " > teardown > clear");
        elements.clear();
        System.gc();
    }
}
