package com.lab2;


import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@State(Scope.Benchmark)
public class PrimitiveSizeState {
    @Param({"10000"})
    public int size;
    private static Random random = new Random();
    private AtomicLong nextElement = new AtomicLong(size);

    public long getExisting(){
        return random.nextInt(size);
    }

    public long getNotExsiting() {
        return nextElement.incrementAndGet();
    }

}
