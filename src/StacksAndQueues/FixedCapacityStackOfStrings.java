package StacksAndQueues;

public class FixedCapacityStackOfStrings {
    private String[] s;
    private int index = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public void push(String value) {
        s[index++] = value;
    }

    public String pop() {
        // dealing with the loitering problem
        String value = s[--index];
        s[index] = null;
        return value;
    }

}
