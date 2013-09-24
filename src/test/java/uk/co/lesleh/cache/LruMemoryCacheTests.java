package uk.co.lesleh.cache;

import static org.junit.Assert.*;

import org.junit.*;

public class LruMemoryCacheTests {

    Cache<String> cache;

    @Before
    public void initialize() {
        cache = new LruMemoryCache<String>(250);
    }

    @Test
    public void testSet() {
        cache.set("a", "aaa", 100);
        assertTrue("Cache should contain 'a'.", cache.containsKey("a"));
        assertEquals(100, cache.size());
    }

    @Test
    public void testRemovalWhenFull() {
        cache.set("a", "aaa", 100);
        assertEquals(100, cache.size());
        cache.set("b", "bbb", 100);
        assertEquals(200, cache.size());
        cache.set("c", "ccc", 100);
        assertEquals(200, cache.size());

        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testRemovalOfLruWhenFull() {
        cache.set("a", "aaa", 100);
        assertEquals(100, cache.size());
        cache.set("b", "bbb", 100);
        assertEquals(200, cache.size());

        cache.get("a"); // Refresh "a"

        cache.set("c", "ccc", 100);
        assertEquals(200, cache.size());
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testClear() {
        cache.set("a", "aaa", 100);
        assertEquals(100, cache.size());

        cache.set("b", "bbb", 100);
        assertEquals(200, cache.size());

        cache.clear();
        assertEquals(0, cache.size());
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

}
