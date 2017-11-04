package com.lab2;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class TestAdd {


    @State(Scope.Benchmark)
    public static class ExistingState {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.existing.get();
        }

        @TearDown(Level.Invocation)
        public void removeOrder(RepoState repoState) {
            repoState.orders.remove(order);
        }
    }

    @State(Scope.Benchmark)
    public static class NotExistingState {
        Order order;

        @Setup(Level.Invocation)
        public void generateOrder(SizeState sizeState) {
            order = sizeState.notExisting.get();
        }

        @TearDown(Level.Invocation)
        public void removeOrder(RepoState repoState) {
            repoState.orders.remove(order);
        }
    }


    @org.openjdk.jmh.annotations.Benchmark
    public void addExistingMethod(ExistingState existingState, RepoState repoState, Blackhole blackhole) {
        repoState.orders.add(existingState.order);
        blackhole.consume(repoState);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void addNotExistingMethod(NotExistingState notExistingState, RepoState repoState, Blackhole blackhole) {
        repoState.orders.add(notExistingState.order);
        blackhole.consume(repoState);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAdd.class.getSimpleName())
//                .addProfiler(HotspotMemoryProfiler.class)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}

