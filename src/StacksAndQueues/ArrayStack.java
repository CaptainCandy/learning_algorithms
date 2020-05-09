package StacksAndQueues;

public class ArrayStack<ValueType> {
    private ValueType[] s;
    private int index = 0;

    public ArrayStack() {
        // use casting because Java doesn't allow generic array declaration
        s = (ValueType[]) new Object[1];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    private void resize(int capacity) {
        ValueType[] s2 = (ValueType[]) new Object[capacity];
        for (int i = 0; i < s.length; i++)
            s2[i] = s[i];
        s = s2;
    }

    public void push(ValueType value) {
        if (index >= s.length) resize(s.length * 2);
        s[index++] = value;
    }

    public ValueType pop() {
        ValueType value = s[--index];
        s[index] = null;
        // halve when the array reach a quarter full
        // to avoid thrashing: pop-push-pop-push-...
        if (index == s.length / 4) resize(s.length / 2);
        return value;
    }
}
