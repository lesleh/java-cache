package uk.co.lesleh.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache<K, V> implements Cache<K, V> {

    private Map<K, V> data = new HashMap<K, V>();

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

        data.put(key, element);
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
}
