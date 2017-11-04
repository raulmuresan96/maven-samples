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
@Warmup(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 8, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class TestContainsPrimitive {


    @State(Scope.Benchmark)
    public static class ExistingState {
        long element;

        @Setup(Level.Invocation)
        public void generateElement(PrimitiveSizeState sizeState) {
            element = sizeState.getExisting();
        }
    }

    @State(Scope.Benchmark)
    public static class NotExistingState {
        long element;

        @Setup(Level.Invocation)
        public void generateElement(PrimitiveSizeState sizeState) {
            element = sizeState.getNotExsiting();
        }
    }

    @org.openjdk.jmh.annotations.Benchmark
    public boolean primitiveContainsExistingMethod(ExistingState existingState, PrimitiveRepoState repoState) {
        return repoState.elements.contains(existingState.element);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public boolean primitiveContainsNotExistingMethod(NotExistingState notExistingState, PrimitiveRepoState repoState) {
        return repoState.elements.contains(notExistingState.element);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestContainsPrimitive.class.getSimpleName())
//                .addProfiler(HotspotMemoryProfiler.class)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}