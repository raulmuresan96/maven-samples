package com.lab2;

import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TLongHashSet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MutableList<Integer> list = Lists.mutable.empty();
        list.add(5);
        list.add(3);
        list.add(6);
        for(Integer nr: list){
            //System.out.println(nr);
        }

        Set<Integer> set = new THashSet<>();
        set.add(10);
        set.add(11);
        set.add(12);
        System.out.println(set);







        //list.ad
    }
}
