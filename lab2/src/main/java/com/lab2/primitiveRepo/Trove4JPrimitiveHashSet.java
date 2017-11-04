package com.lab2.primitiveRepo;

import com.lab2.primitiveRepo.InMemoryRepositoryPrimitive;
import gnu.trove.set.hash.TLongHashSet;

public class Trove4JPrimitiveHashSet implements InMemoryRepositoryPrimitive {

    private TLongHashSet set;
    public Trove4JPrimitiveHashSet(){
        set = new TLongHashSet();
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
