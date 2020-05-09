package StacksAndQueues;

// use generics
public class LinkedStack<ValueType> {
    // use an inner class to define the node of a linked list
    private class Node {
        ValueType value;
        Node next;
    }

    private Node first = null;

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
