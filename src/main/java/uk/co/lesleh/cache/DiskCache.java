package uk.co.lesleh.cache;

import java.io.File;

public abstract class DiskCache<K, V> implements Cache<K, V> {

    private final File directory;

    private final int maxSize;

    private int size;

    public DiskCache(File directory, int maxSize) {
        if(!directory.exists())
            throw new IllegalArgumentException("Directory does not exist.");

        if(directory.exists() && !directory.isDirectory())
            throw new IllegalArgumentException("Not a directory.");

        this.directory = directory;
        this.maxSize = maxSize;
    }

    @Override
    public V get(K key) {
        // TODO implement get()
    }

    @Override
    public void put(K key, V element) {
        // TODO implement put()
    }

    @Override
    public V remove(K key) {
        // TODO implement remove()
    }

    @Override
    public boolean containsKey(K key) {
         // TODO implement containsKey()
    }

    @Override
    public void clear() {
        // TODO empty directory
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int maxSize() {
        return maxSize;
    }

    abstract byte[] serialize(V value);

    abstract V deserialize(byte[] bytes);

    abstract String getFilenameFromKey(K key);
}
