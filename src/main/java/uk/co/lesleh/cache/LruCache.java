package uk.co.lesleh.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Decorator for any Cache
public abstract class LruCache<K, V> implements Cache<K, V> {

    private Cache<K, V> backingCache;

    private Queue<K> lruData = new LinkedList<K>();

    private Map<K, Integer> sizes = new HashMap<K, Integer>();

    private final int maxSize;

    private int size;

    public int getSize() {
        return size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public LruCache(Cache<K, V> backingCache, int maxSize) {
        this.backingCache = backingCache;
        this.maxSize = maxSize;
    }

    @Override
    public V get(K key) {
        V element = backingCache.get(key);

        lruData.remove(key);
        lruData.add(key);

        return element;
    }

    @Override
    public void put(K key, V element) {
        if (backingCache.containsKey(key)) {
            remove(key);
        }

        backingCache.put(key, element);
        lruData.add(key);
        int elementSize = sizeOf(key, element);
        sizes.put(key, elementSize);
        size += elementSize;

        reduceToSize();
    }

    private void reduceToSize() {
        while (size > maxSize) {
            remove(lruData.peek());
        }
    }

    @Override
    public void remove(K key) {
        backingCache.remove(key);
        lruData.remove(key);
        size -= sizes.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return backingCache.containsKey(key);
    }

    @Override
    public void clear() {
        backingCache.clear();
        lruData.clear();
        sizes.clear();
        size = 0;
    }

    abstract int sizeOf(K key, V value);
}
