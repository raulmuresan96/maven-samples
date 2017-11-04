package com.lab2.primitiveRepo;

import com.koloboke.collect.set.hash.HashLongSet;
import com.koloboke.collect.set.hash.HashLongSets;
import com.lab2.primitiveRepo.InMemoryRepositoryPrimitive;

public class KolobokeLongHashSet implements InMemoryRepositoryPrimitive {
    private HashLongSet set;

    public KolobokeLongHashSet(){
        set = HashLongSets.newMutableSet();
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
        set.removeLong(e);
    }

    @Override
    public void clear() {
        set.clear();
    }
}
