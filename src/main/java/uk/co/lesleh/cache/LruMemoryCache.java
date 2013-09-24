package uk.co.lesleh.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class LruMemoryCache<E> implements Cache<E> {
    private final int maxSize;

    private final Map<String, Entry<E>> data = new HashMap<String, Entry<E>>();

    private Queue<String> lruStatus = new LinkedList<String>();

    private int currentSize = 0;

    public LruMemoryCache(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public E get(String key) {
        Entry<E> entry = data.get(key);
        lruStatus.remove(key);
        lruStatus.add(key);
        return entry.data;
    }

    @Override
    public void set(String key, E element) {
        int size = calculateSize(element);
        data.put(key, new Entry<E>(element, size));
        lruStatus.add(key);
        currentSize += size;
        trim();
    }

    @Override
    public void remove(String key) {
        Entry<E> entry = data.get(key);
        if(entry != null) {
            data.remove(key);
            lruStatus.remove(key);
            currentSize -= entry.size;
        }
    }

    @Override
    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    @Override
    public void clear() {
        data.clear();
        lruStatus.clear();
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    private void trim() {
        while(currentSize > maxSize) {
            remove(lruStatus.peek());
        }
    }

    public abstract int calculateSize(E element);

    private static class Entry<E> {

        final E data;

        final int size;

        private Entry(E data, int size) {
            this.data = data;
            this.size = size;
        }

    }
}
