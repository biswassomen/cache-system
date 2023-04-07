package cache.storage;

import cache.exceptions.InvalidCapacityException;
import cache.exceptions.StorageFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapBasedStorageTest {

    @Test
    void invalidCapacityConstructorException() {
        assertThrows(InvalidCapacityException.class, () -> new HashMapBasedStorage<>(-1));
    }
    @Test
    void addTwoKeysWithCapacityFive() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(5);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        assertEquals(2, storage.getSize());
    }

    @Test
    void storageFullException() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(2);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        assertThrows(StorageFullException.class, () -> storage.add("k3", "val3"));
    }

    @Test
    void updateValueForSameKeyAdd() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(3);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        storage.add("k2", "val3");
        assertEquals(2, storage.getSize());
    }

    @Test
    void getValueForPresentKey() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(3);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        assertEquals("val1", storage.get("k1"));
    }

    @Test
    void getNullValueForUnknownKey() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(3);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        assertNull(storage.get("k4"));
    }

    @Test
    void removeKnownKey() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(3);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        storage.remove("k1");
        assertEquals(1, storage.getSize());
    }

    @Test
    void removeUnknownKey() {
        HashMapBasedStorage<String, String> storage = new HashMapBasedStorage<>(3);
        storage.add("k1", "val1");
        storage.add("k2", "val2");
        storage.remove("k3");
        assertEquals(2, storage.getSize());
    }
}