package uk.co.lesleh.cache;

public interface Cache<K, V> {

    /**
     * Returns the stored value for the given key, if there is one. If no value exists for a given key, an exception is
     * thrown.
     *
     * @param key the key to retrieve
     * @return the value for the given key
     * @throws java.util.NoSuchElementException if the key doesn't exist in the cache
     * @throws java.lang.NullPointerException if the key is null
     */
    V get(K key);

    /**
     * Puts the given value into the cache, at the given key. If an object already exists with this key, it will be
     * replaced.
     *
     * @param key the key to store at
     * @param element the value to store
     * @throws java.lang.NullPointerException if the key or element is null
     */
    void put(K key, V element);

    /**
     * Removes the value at the given key and returns it. If no value exists with that key, an exception is thrown.
     *
     * @param key the key to remove
     * @return the value at the given key
     * @throws java.util.NoSuchElementException if the key doesn't exist in the cache
     * @throws java.lang.NullPointerException if the key is null
     */
    V remove(K key);

    /**
     * Checks to see if a value exists in the cache with the given key.
     *
     * @param key the key to search for
     * @return true if a value exists, false otherwise
     * @throws java.lang.NullPointerException if the key is null
     */
    boolean containsKey(K key);

    /**
     * Removes all elements from the cache.
     */
    void clear();

    /**
     * Returns the current size of the cache. Calculation of the size is implementation specific.
     *
     * @return the current size
     */
    int size();

    /**
     * Returns the maximum size that this cache will allow to be stored.
     *
     * @return the maximum size of the cache
     */
    int maxSize();

}
