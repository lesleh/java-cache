package uk.co.lesleh.cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LruCacheTests {

    private final int MAX_SIZE = 25;

    LruCache<String> cache;

    @Before
    public void initialize() {
        cache = new LruCache<String>(new MemoryCache<String>(), MAX_SIZE) {
            @Override
            int calculateSize(String element) {
                return element.length();
            }
        };
    }

    @Test
    public void testSet() {
        cache.set("a", "a");
        assertTrue("Cache should contain 'a'.", cache.containsKey("a"));
    }

    @Test
    public void testSettingKeyAgain() {
        cache.set("a", "a");
        cache.set("a", "bb");
        assertEquals("bb", cache.get("a"));
        assertEquals(2, cache.getSize());
    }

    @Test
    public void testRemove() {
        cache.set("a", "aaa");
        cache.remove("a");
        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testRemovalWhenFull() {
        cache.set("a", "1234567890");
        cache.set("b", "1234567890");
        cache.set("c", "1234567890");

        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testRemovalOfLruWhenFull() {
        cache.set("a", "1234567890");
        cache.set("b", "1234567890");

        cache.get("a"); // Refresh "a"

        cache.set("c", "1234567890");
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testClear() {
        cache.set("a", "a");
        cache.set("b", "b");

        cache.clear();
        assertEquals(0, cache.getSize());
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testSize() {
        cache.set("a", "a");
        assertEquals(1, cache.getSize());
        cache.set("b", "bb");
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
        cache.set(null, "a");
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullValue() {
        cache.set("a", null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullKey() {
        cache.remove(null);
    }


}
