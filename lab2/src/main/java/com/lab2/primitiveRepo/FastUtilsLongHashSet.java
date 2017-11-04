package com.lab2.primitiveRepo;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;

public class FastUtilsLongHashSet implements InMemoryRepositoryPrimitive {

    private LongOpenHashSet set;

    public FastUtilsLongHashSet(){
        set = new LongOpenHashSet();
    }

    @Override
    public void add(long e) {
        set.add(e);
    }

    @Override
    public boolean contains(long e) {
        return set.contains(e);
    }

    @Override
    public void remove(long e) {
        set.remove(e);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
