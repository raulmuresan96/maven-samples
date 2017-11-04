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
public class TestAddPrimitive {


    @State(Scope.Benchmark)
    public static class ExistingState {
        long element;

        @Setup(Level.Invocation)
        public void generateElement(PrimitiveSizeState sizeState) {
            element = sizeState.getExisting();
        }

        @TearDown(Level.Invocation)
        public void removeElement(PrimitiveRepoState repoState) {
            repoState.elements.remove(element);
        }
    }

    @State(Scope.Benchmark)
    public static class NotExistingState {
        long element;

        @Setup(Level.Invocation)
        public void generateElement(PrimitiveSizeState sizeState) {
            element = sizeState.getNotExsiting();
        }

        @TearDown(Level.Invocation)
        public void removeElement(PrimitiveRepoState repoState) {
            repoState.elements.remove(element);
        }
    }


    @org.openjdk.jmh.annotations.Benchmark
    public void primitiveAddExistingMethod(ExistingState existingState, PrimitiveRepoState repoState, Blackhole blackhole) {
        repoState.elements.add(existingState.element);
        blackhole.consume(repoState);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void primitiveAddNotExistingMethod(NotExistingState notExistingState, PrimitiveRepoState repoState, Blackhole blackhole) {
        repoState.elements.add(notExistingState.element);
        blackhole.consume(repoState);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TestAddPrimitive.class.getSimpleName())
//                .addProfiler(HotspotMemoryProfiler.class)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
