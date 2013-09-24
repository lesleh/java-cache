package uk.co.lesleh.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// Decorator for any Cache
public abstract class LruCache<E> implements Cache<E> {

    private Cache<E> backingCache;

    private Queue<String> lruData = new LinkedList<String>();

    private Map<String, Integer> sizes = new HashMap<String, Integer>();

    private final int maxSize;

    private int size;

    public int getSize() {
        return size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public LruCache(Cache<E> backingCache, int maxSize) {
        this.backingCache = backingCache;
        this.maxSize = maxSize;
    }

    @Override
    public E get(String key) {
        E element = backingCache.get(key);

        lruData.remove(key);
        lruData.add(key);

        return element;
    }

    @Override
    public void set(String key, E element) {
        if (backingCache.containsKey(key)) {
            remove(key);
        }

        backingCache.set(key, element);
        lruData.add(key);
        int elementSize = calculateSize(element);
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
    public void remove(String key) {
        backingCache.remove(key);
        lruData.remove(key);
        size -= sizes.remove(key);
    }

    @Override
    public boolean containsKey(String key) {
        return backingCache.containsKey(key);
    }

    @Override
    public void clear() {
        backingCache.clear();
        lruData.clear();
        sizes.clear();
        size = 0;
    }

    abstract int calculateSize(E element);
}
