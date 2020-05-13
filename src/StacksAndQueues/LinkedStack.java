package StacksAndQueues;

import java.util.Iterator;

// use generics
public class LinkedStack<ValueType> implements Iterable<ValueType> {
    // use an inner class to define the node of a linked list
    private class Node {
        ValueType value;
        Node next;
    }

    private class ListIterator implements Iterator<ValueType> {
        private Node current = first;

        public boolean hasNext() { return current != null;}

        public ValueType next() {
            ValueType value = current.value;
            current = current.next;
            return value;
        }

        public void remove() { }

    }

    private Node first = null;

    @Override
    public Iterator<ValueType> iterator() {
        return new ListIterator();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(ValueType value) {
        Node oldFirst = first;
        first = new Node();
        first.value = value;
        first.next = oldFirst;
    }

    public ValueType pop() {
        ValueType value = first.value;
        // point the first node to the second
        // wait for the garbage collection to clean the old first node
        first = first.next;
        return value;
    }
}
