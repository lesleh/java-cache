# Java Cache

A collection of caching classes I wrote to handle various cache storage methods, as well as different cache eviction algorithms.

## Example

    int maxCacheSize = 100;
    Cache<String, String> cache = new MemoryCache<String, String>(maxCacheSize) {
        @Override
        public int sizeOf(String key, String value) {
            return value.length();
        }
    }

    cache.set("b", "set-b");
    String value = cache.get("b");
