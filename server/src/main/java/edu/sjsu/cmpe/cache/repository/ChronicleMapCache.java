package edu.sjsu.cmpe.cache.repository;

import edu.sjsu.cmpe.cache.domain.Entry;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ChronicleMapCache implements CacheInterface {

    private final ChronicleMap<Long, Entry> chronicleMapCache;

    public ChronicleMapCache(ChronicleMap<Long, Entry> cacheMap) {
        chronicleMapCache = cacheMap;
    }

    @Override
    public Entry save(Entry newEntry) {
        checkNotNull(newEntry, "newEntry instance must not be null");
        chronicleMapCache.putIfAbsent(newEntry.getKey(), newEntry);

        return newEntry;
    }

    @Override
    public Entry get(Long key) {
        checkArgument(key > 0, "Key was %s but expected greater than zero value", key);
        return chronicleMapCache.get(key);
    }

    @Override
    public List<Entry> getAll() {
        return new ArrayList<Entry>(chronicleMapCache.values());
    }
}