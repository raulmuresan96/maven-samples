package com.lab2;

import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@State(Scope.Benchmark)
public class SizeState {
    @Param({"10000"})

    public int size;

    public Supplier<Order> existing = new Supplier<Order>() {
        private final Random random = new Random();

        @Override
        public Order get() {
            return new Order(random.nextInt(size));
        }
    };

    public Supplier<Order> notExisting = new Supplier<Order>() {
        private final AtomicInteger nextElement = new AtomicInteger(size);

        @Override
        public Order get() {
            return new Order(nextElement.incrementAndGet());
        }
    };


}
