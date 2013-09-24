package uk.co.lesleh.cache;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemoryCacheTests {

    MemoryCache<String> cache;

    @Before
    public void initialize() {
        cache = new MemoryCache<String>();
    }

    @Test
    public void testSet() {
        cache.set("a", "a");
        assertTrue("Cache should contain 'a'.", cache.containsKey("a"));
    }

    @Test
    public void testRemove() {
        cache.set("a", "aaa");
        cache.remove("a");
        assertFalse(cache.containsKey("a"));
    }

    @Test
    public void testClear() {
        cache.set("a", "a");
        cache.set("b", "b");

        cache.clear();
        assertFalse(cache.containsKey("a"));
        assertFalse(cache.containsKey("b"));
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
