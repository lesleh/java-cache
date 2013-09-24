package uk.co.lesleh.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache<E> implements Cache<E> {

    private Map<String, E> data = new HashMap<String, E>();

    @Override
    public E get(String key) {
        if (key == null)
            throw new NullPointerException();

        return data.get(key);
    }

    @Override
    public void set(String key, E element) {
        if (key == null || element == null)
            throw new NullPointerException();

        data.put(key, element);
    }

    @Override
    public void remove(String key) {
        data.remove(key);
    }

    @Override
    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    @Override
    public void clear() {
        data.clear();
    }
}
