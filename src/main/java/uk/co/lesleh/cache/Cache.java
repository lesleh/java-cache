package uk.co.lesleh.cache;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V element);

    V remove(K key);

    boolean containsKey(K key);

    void clear();

    int size();

    int maxSize();

}
