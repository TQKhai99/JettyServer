package com.khaitq2.cache;

import com.khaitq2.songservice.SongStruct;

import java.util.LinkedList;
import java.util.List;

public class Cache {
    private static Cache instance;
    private final LinkedList<SongStruct> cache; // dung linkedlist de dung addFirst

    private Cache() {
        cache = new LinkedList<>();
    }

    public static Cache getInstance() {
        return (instance == null) ? new Cache() : instance;
    }

    public boolean contains(int id) {
        for (SongStruct i : cache) {
            if (i.id == id) {
                return true;
            }
        }
        return false;
    }

    public SongStruct getSong(int id) {
        for (SongStruct i : cache) {
            if (i.id == id) {
                return i;
            }
        }
        return null;
    }

    public void setCache(SongStruct song) {
        cache.addLast(song);
        if (cache.size() > 5) {
            _removeCache();
        }
    }

    private void _removeCache() {
        cache.remove(cache.get(0));
    }

    // test
    public List<SongStruct> getCache() {
        return cache;
    }
}
