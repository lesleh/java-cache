package uk.co.lesleh.cache;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V element);

    void remove(K key);

    boolean containsKey(K key);

    void clear();

}
