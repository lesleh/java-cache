package uk.co.lesleh.cache;

import static org.junit.Assert.*;

import org.junit.*;

public class LruMemoryCacheTests {

    private final int MAX_SIZE = 25;

    Cache<String> cache;

    @Before
    public void initialize() {
        cache = new LruMemoryCache<String>(MAX_SIZE) {
            @Override
            public int calculateSize(String element) {
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
        assertEquals(2, cache.size());
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
        assertEquals(0, cache.size());
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testSize() {
        cache.set("a", "a");
        assertEquals(1, cache.size());
        cache.set("b", "bb");
        assertEquals(3, cache.size());
    }

    @Test
    public void testMaxSize() {
        assertEquals(MAX_SIZE, cache.maxSize());
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

}
