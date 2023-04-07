package cache.policies;

import cache.models.DoublyLLNode;
import cache.models.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    private Map<Key, DoublyLLNode<Key>> mappings;
    private DoublyLinkedList<Key> doublyLinkedList;

    public LRUEvictionPolicy() {
        this.mappings = new HashMap<>();
        this.doublyLinkedList = new DoublyLinkedList();
    }

    @Override
    public void keyAccessed(Key key) {
        if(mappings.containsKey(key)) {
            DoublyLLNode node = mappings.get(key);
            doublyLinkedList.remove(node);
            doublyLinkedList.addFirst(node);
        } else {
            DoublyLLNode<Key> node = new DoublyLLNode<>(key);
            doublyLinkedList.addFirst(node);
            mappings.put(key, node);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLLNode<Key> lastNode = doublyLinkedList.removeLast();
        if(lastNode != null) {
            mappings.remove(lastNode.getVal());
            return lastNode.getVal();
        }
        return null;
    }

    @Override
    public boolean removeKey(Key key) {
        DoublyLLNode<Key> node = mappings.remove(key);
        if(node == null)
            return false;
        doublyLinkedList.remove(node);
        return true;
    }
}
