package cache.models;

public class DoublyLLNode<V> {

    private V val;
    private DoublyLLNode<V> previous;

    private DoublyLLNode<V> next;

    public DoublyLLNode(V val) {
        this.val = val;
    }

    public DoublyLLNode() {}

    public DoublyLLNode<V> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyLLNode<V> previous) {
        this.previous = previous;
    }

    public DoublyLLNode<V> getNext() {
        return next;
    }

    public void setNext(DoublyLLNode<V> next) {
        this.next = next;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }
}
