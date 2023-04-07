package cache.policies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LRUEvictionPolicyTest {

    @Test
    void keysEvictedInOrderAsAdded() {
        LRUEvictionPolicy policy = new LRUEvictionPolicy();
        policy.keyAccessed("k1");
        policy.keyAccessed("k2");
        assertEquals("k1", policy.evictKey());
        assertEquals("k2", policy.evictKey());
    }

    @Test
    void keysEvictedIOrderOfLeastRecentlyAccessed() {
        LRUEvictionPolicy policy = new LRUEvictionPolicy();
        policy.keyAccessed("k1");
        policy.keyAccessed("k2");
        policy.keyAccessed("k3");
        policy.keyAccessed("k1");
        assertEquals("k2", policy.evictKey());
        assertEquals("k3", policy.evictKey());
        assertEquals("k1", policy.evictKey());
    }

    @Test
    void removeKey() {
        LRUEvictionPolicy policy = new LRUEvictionPolicy();
        policy.keyAccessed("k1");
        policy.removeKey("k1");
        assertNull(policy.evictKey());
    }
}