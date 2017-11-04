package com.lab2;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

//We do not need @TearDown here because we only check if a certain Order is present in the collection
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 8, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class TestContains {

    @State(Scope.Benchmark)
    public static class ExistingState {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.existing.get();
        }
    }

    @State(Scope.Benchmark)
    public static class NotExistingState {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.notExisting.get();
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void containsExistingMethod(ExistingState existingState, RepoState repoState, Blackhole blackhole) {
        blackhole.consume(repoState.orders.contains(existingState.order));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void containsNotExistingMethod(NotExistingState notExistingState, RepoState repoState, Blackhole blackhole) {
        blackhole.consume(repoState.orders.contains(notExistingState.order));

    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestContains.class.getSimpleName())
//                .addProfiler(HotspotMemoryProfiler.class)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
