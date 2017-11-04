package com.lab2;

import com.lab2.repositories.InMemoryRepository;
import org.openjdk.jmh.annotations.*;

import java.util.stream.IntStream;


//Scope.Benchmark - All threads running the benchmark share the same state object.
@State(Scope.Benchmark)
public class RepoState {
    @Param
    private RepositorySupplier repositorySupplier;

    public InMemoryRepository<Order> orders;

    //we initialize the InMemoryRepository
    @Setup(Level.Iteration)
    public void setUp(SizeState sizeState) {
        System.out.println(getClass().getSimpleName() + " > setup > populate");
        orders = repositorySupplier.get();

        //IntStream iterates over all ints from 1 to sizeState.size, creates a new order for every int in the range,
        //and adds the orders to orders repository
        IntStream.rangeClosed(1, sizeState.size)
                .mapToObj(Order::new)
                .forEach(orders::add);
    }

    /* code gets executed after every iteration */
    @TearDown(Level.Iteration)
    public void tearDown() {
        System.out.println(getClass().getSimpleName() + " > teardown > clear");
        orders.clear();
        System.gc();
    }
}
