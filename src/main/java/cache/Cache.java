package cache;

import cache.exceptions.StorageFullException;
import cache.policies.EvictionPolicy;
import cache.storage.Storage;

public class Cache<Key, Value> {
    private EvictionPolicy<Key> evictionPolicy;
    private Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    // GET
    public Value get(Key key) {
        Value val = storage.get(key);
        if(val == null) {
            return null;
        }
        evictionPolicy.keyAccessed(key);
        return val;
    }

    // SET
    public void set(Key key, Value value){
        try {
            storage.add(key, value);
        } catch (StorageFullException ex) {
            Key lastNodeKey = evictionPolicy.evictKey();
            storage.remove(lastNodeKey);
            storage.add(key, value);
        }
        evictionPolicy.keyAccessed(key);
    }

    // REMOVE
    public void remove(Key key) {
        storage.remove(key);
        evictionPolicy.removeKey(key);
    }
}
