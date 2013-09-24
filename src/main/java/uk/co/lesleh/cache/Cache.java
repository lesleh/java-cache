package uk.co.lesleh.cache;

public interface Cache<E> {

    E get(String key);

    void set(String key, E element, int size);

    void remove(String key);

    boolean containsKey(String key);

    void clear();

}
