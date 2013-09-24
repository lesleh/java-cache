package uk.co.lesleh.cache;

import static org.junit.Assert.*;

import org.junit.*;

public class LruMemoryCacheTests {

    private final int MAX_SIZE = 250;

    private final int ELEMENT_SIZE = 100;

    Cache<String> cache;

    @Before
    public void initialize() {
        cache = new LruMemoryCache<String>(MAX_SIZE) {
            @Override
            public int calculateSize(String element) {
                return ELEMENT_SIZE;
            }
        };
    }

    @Test
    public void testSet() {
        cache.set("a", "aaa");
        assertTrue("Cache should contain 'a'.", cache.containsKey("a"));
        assertEquals(100, cache.size());
    }

    @Test
    public void testRemovalWhenFull() {
        cache.set("a", "aaa");
        cache.set("b", "bbb");
        cache.set("c", "ccc");

        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testRemovalOfLruWhenFull() {
        cache.set("a", "aaa");
        cache.set("b", "bbb");

        cache.get("a"); // Refresh "a"

        cache.set("c", "ccc");
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testClear() {
        cache.set("a", "aaa");
        cache.set("b", "bbb");

        cache.clear();
        assertEquals(0, cache.size());
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
    }

    @Test
    public void testSize() {
        cache.set("a", "aaa");
        assertEquals(ELEMENT_SIZE * 1, cache.size());
        cache.set("b", "bbb");
        assertEquals(ELEMENT_SIZE * 2, cache.size());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNullKey() {
        cache.get(null);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullKey() {
        cache.set(null, "aaa");
    }

    @Test(expected = NullPointerException.class)
    public void testSetNullValue() {
        cache.set("a", null);
    }

}
