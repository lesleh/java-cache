package uk.co.lesleh.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache<K, V> implements Cache<K, V> {

    private Map<K, V> data = new HashMap<K, V>();

    private int size;

    @Override
    public V get(K key) {
        if (key == null)
            throw new NullPointerException();

        return data.get(key);
    }

    @Override
    public void put(K key, V element) {
        if (key == null || element == null)
            throw new NullPointerException();

        int elementSize = sizeOf(key, element);
        data.put(key, element);
        size += elementSize;
    }

    @Override
    public V remove(K key) {
        return data.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return data.containsKey(key);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int maxSize() {
        return Integer.MAX_VALUE;
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }
}
