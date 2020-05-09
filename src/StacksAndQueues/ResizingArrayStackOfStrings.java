package StacksAndQueues;

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int index = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    private void resize(int capacity) {
        String[] s2 = new String[capacity];
        for (int i = 0; i < s.length; i++)
            s2[i] = s[i];
        s = s2;
    }

    public void push(String value) {
        if (index >= s.length) resize(s.length * 2);
        s[index++] = value;
    }

    public String pop() {
        String value = s[--index];
        s[index] = null;
        // halve when the array reach a quarter full
        // to avoid thrashing: pop-push-pop-push-...
        if (index == s.length / 4) resize(s.length / 2);
        return value;
    }

}
