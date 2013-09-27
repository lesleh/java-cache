package uk.co.lesleh.cache;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MemoryCacheTests {

    MemoryCache<String, String> cache;

    @Before
    public void initialize() {
        cache = new MemoryCache<String, String>() {
            @Override
            protected int sizeOf(String key, String value) {
                return value.length();
            }
        };
    }

    @Test
    public void testSet() throws IOException {
        cache.put("a", "a");
        assertTrue("Cache should contain 'a'.", cache.containsKey("a"));
    }

    @Test
    public void testRemove() throws IOException {
        cache.put("a", "aaa");
        cache.remove("a");
        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testClear() throws IOException {
        cache.put("a", "a");
        cache.put("b", "b");

        cache.clear();
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testSize() throws IOException {
        cache.put("a", "aaa");
        assertEquals(3, cache.size());
    }

    @Test
    public void testMaxSize() throws IOException {
        cache.put("a", "aaa");
        assertEquals(Integer.MAX_VALUE, cache.maxSize());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNullKey() throws IOException {
        cache.get(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullKey() throws IOException {
        cache.put(null, "a");
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullValue() throws IOException {
        cache.put("a", null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullKey() throws IOException {
        cache.remove(null);
    }

}
