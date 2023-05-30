// Generic Linked Stack for usage in Calculator.java

// defines methods needed
interface GenericLink<E> {
    E pop();
    void push(E val);
    int size();
    boolean isEmpty();
    E peek();
}

public class GenericLinkedStack<E> implements GenericLink<E>{
     class Node<E> {
        private E value;
        private Node<E> next;

        Node(E val, Node<E> next) {
            this.value = val;
            this.next = next;
        }
    }
    // hold top node and size
    Node<E> top;
    int size;

    // instantiate top as null and size as 0
    public GenericLinkedStack() {
        this.top = null;
        this.size = 0;

    }

    // point the new top at the old top
    public void push(E val) {
        Node<E> newTop = new Node<>(val, this.top);
        this.top = newTop;
        this.size++;

    }

    // get top value and decrement top
    public E pop() {
        if (this.size > 0) {
            E ret = this.top.value;
            this.top = this.top.next;
            size--;
            return ret;
        }
        else {
            return null;
        }

    }

    // look at top value
    public E peek() {
        if (this.size > 0) {
            return this.top.value;
        }
        else {
            return null;
        }

    }

    // get size
    public int size() {
        return this.size;
    }

    // check if size is 0
    public boolean isEmpty() {
        return this.size == 0;

    }

}
