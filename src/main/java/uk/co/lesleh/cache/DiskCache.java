package uk.co.lesleh.cache;

import java.io.File;
import java.io.IOException;

public abstract class DiskCache<K, V> implements Cache<K, V> {

    private final File directory;

    private final int maxSize;

    private int size;

    public DiskCache(File directory, int maxSize) {
        if (!directory.exists())
            throw new IllegalArgumentException("Directory does not exist.");

        if (directory.exists() && !directory.isDirectory())
            throw new IllegalArgumentException("Not a directory.");

        this.directory = directory;
        this.maxSize = maxSize;
    }

    @Override
    public V get(K key) throws IOException {
        // TODO implement get()
        throw new RuntimeException("Not implemented.");
        /*RandomAccessFile f = new RandomAccessFile(getFilenameFromKey(key), "r");
        byte[] b = new byte[(int)f.length()];
        f.read(b);
        return deserialize(b);*/
    }

    @Override
    public void put(K key, V element) throws IOException {
        // TODO implement put()
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public V remove(K key) throws IOException {
        // TODO implement remove()
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public boolean containsKey(K key) throws IOException {
        File file = new File(directory, getFilenameFromKey(key));
        return file.exists();
    }

    @Override
    public void clear() throws IOException {
        // TODO empty directory
        throw new RuntimeException("Not implemented.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int maxSize() {
        return maxSize;
    }

    abstract byte[] serialize(V value) throws IOException;

    abstract V deserialize(byte[] bytes) throws IOException;

    abstract String getFilenameFromKey(K key);
}
