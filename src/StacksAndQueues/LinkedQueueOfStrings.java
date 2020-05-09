package StacksAndQueues;

public class LinkedQueueOfStrings {
    // use an inner class to define the node of a linked list
    private class Node {
        String value;
        Node next;
    }

    private Node first, last;

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(String value) {
        Node oldLast = last;
        last = new Node();
        last.value = value;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public String dequeue() {
        String value = first.value;
        first = first.next;
        if (isEmpty()) last = null;
        return value;
    }
}
