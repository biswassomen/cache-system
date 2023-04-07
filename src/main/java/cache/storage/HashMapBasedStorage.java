package cache.storage;

import cache.exceptions.InvalidCapacityException;
import cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value>{

    private Map<Key, Value> elements;

    private final int capacity;

    public HashMapBasedStorage(int capacity) {
        if(capacity < 1) {
            throw new InvalidCapacityException("Storage capacity should be 1 or above.");
        }
        this.elements = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void add(Key key, Value value) {
        if(elements.containsKey(key)) {
            elements.put(key, value);
            return;
        }
        if(elements.size() == capacity) {
            throw new StorageFullException("Storage capacity is reached.");
        }
        elements.put(key, value);
    }

    @Override
    public void remove(Key key) {
        elements.remove(key);
    }

    @Override
    public Value get(Key key) {
        return elements.get(key);
    }

    public int getSize() {return elements.size();}
}
