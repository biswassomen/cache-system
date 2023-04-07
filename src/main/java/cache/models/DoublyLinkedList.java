package cache.models;

public class DoublyLinkedList<Value> {
    private DoublyLLNode<Value> head;

    private DoublyLLNode<Value> tail;

    private int size;

    public int getSize() {
        return size;
    }

    public DoublyLinkedList() {
        this.head = new DoublyLLNode<Value>();
        this.tail = new DoublyLLNode<Value>();
        head.setNext(tail);
        tail.setPrevious(head);
    }

    public void addFirst(DoublyLLNode<Value> node) {
        DoublyLLNode<Value> first = head.getNext();
        this.head.setNext(node);
        node.setNext(first);
        node.setPrevious(head);
        first.setPrevious(node);
        this.size++;
    }

    public DoublyLLNode<Value> removeLast() {
        DoublyLLNode<Value> last = tail.getPrevious();
        if(last == head) {
            return null;
        }
        last.getPrevious().setNext(tail);
        tail.setPrevious(last.getPrevious());
        last.setPrevious(null);
        last.setNext(null);
        this.size--;

        return last;
    }

    public void remove(DoublyLLNode<Value> node) {
        DoublyLLNode<Value> prev = node.getPrevious();
        DoublyLLNode<Value> next = node.getNext();

        prev.setNext(next);
        next.setPrevious(prev);
        node.setNext(null);
        node.setPrevious(null);

        this.size--;
    }

}
