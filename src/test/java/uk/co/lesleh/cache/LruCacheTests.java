package uk.co.lesleh.cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LruCacheTests {

    private final int MAX_SIZE = 25;

    LruCache<String, String> cache;

    @Before
    public void initialize() {
        cache = new LruCache<String, String>(new MemoryCache<String, String>(), MAX_SIZE) {
            @Override
            int sizeOf(String key, String value) {
                return value.length();
            }
        };
    }

    @Test
    public void testSet() {
        cache.put("a", "a");
        assertTrue("Cache should contain 'a'.", cache.containsKey("a"));
    }

    @Test
    public void testSettingKeyAgain() {
        cache.put("a", "a");
        cache.put("a", "bb");
        assertEquals("bb", cache.get("a"));
        assertEquals(2, cache.getSize());
    }

    @Test
    public void testRemove() {
        cache.put("a", "aaa");
        cache.remove("a");
        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testRemovalWhenFull() {
        cache.put("a", "1234567890");
        cache.put("b", "1234567890");
        cache.put("c", "1234567890");

        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testRemovalOfLruWhenFull() {
        cache.put("a", "1234567890");
        cache.put("b", "1234567890");

        cache.get("a"); // Refresh "a"

        cache.put("c", "1234567890");
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testClear() {
        cache.put("a", "a");
        cache.put("b", "b");

        cache.clear();
        assertEquals(0, cache.getSize());
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testSize() {
        cache.put("a", "a");
        assertEquals(1, cache.getSize());
        cache.put("b", "bb");
        assertEquals(3, cache.getSize());
    }

    @Test
    public void testMaxSize() {
        assertEquals(MAX_SIZE, cache.getMaxSize());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNullKey() {
        cache.get(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullKey() {
        cache.put(null, "a");
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullValue() {
        cache.put("a", null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullKey() {
        cache.remove(null);
    }


}
