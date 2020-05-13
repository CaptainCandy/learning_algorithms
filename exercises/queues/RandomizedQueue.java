/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // inner class node 16+8*2+8+8=48 bytes memory
    private class Node {
        Item item;
        Node next;
        Node prior;
    }

    private class ListIterator implements Iterator<Item> {
        private int lastIndex;
        private Item[] temp;

        public ListIterator() {
            lastIndex = size - 1;
            temp = (Item[]) new Object[size];
            Node current = first;
            for (int i = 0; i < size; i++) {
                temp[i] = current.item;
                current = current.next;
            }
        }

        public boolean hasNext() { return lastIndex >= 0; }

        public Item next() {
            if (hasNext()) {
                int target = StdRandom.uniform(lastIndex + 1);
                Item item = temp[target];
                temp[target] = temp[lastIndex];
                temp[lastIndex] = item;
                lastIndex--;
                return item;
            }
            else throw new NoSuchElementException("There is no next item. ");
        }

        public void remove() {
            throw new UnsupportedOperationException("This method is not supported. ");
        }
    }

    private Node first;
    private Node last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() { }

    // is the randomized queue empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return size; }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("The item is null.");
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else {
                last.prior = oldLast;
                oldLast.next = last;
            }
            size++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("The RandomQueue is empty.");
        else {
            if (size == 1) {
                Item item = first.item;
                first = null;
                last = null;
                size--;
                return item;
            }
            else {
                int i = StdRandom.uniform(size) + 1;
                if (i > size / 2) {
                    if (size == 2) {
                        Item item = last.item;
                        last = first;
                        last.prior = null;
                        first.next = null;
                        size--;
                        return item;
                    }
                    else {
                        if (i == size) {
                            Item item = last.item;
                            last = last.prior;
                            last.next = null;
                            size--;
                            return item;
                        }
                        else {
                            Node current = last;
                            for (int j = 0; j < size - i; j++) {
                                current = current.prior;
                            }
                            current.prior.next = current.next;
                            current.next.prior = current.prior;
                            size--;
                            return current.item;
                        }
                    }
                }
                else {
                    if (size == 2) {
                        Item item = first.item;
                        first = last;
                        last.prior = null;
                        first.next = null;
                        size--;
                        return item;
                    }
                    else {
                        if (i == 1) {
                            Item item = first.item;
                            first = first.next;
                            first.prior = null;
                            size--;
                            return item;
                        }
                        else {
                            Node current = first;
                            for (int j = 0; j < i - 1; j++) {
                                current = current.next;
                            }
                            current.prior.next = current.next;
                            current.next.prior = current.prior;
                            size--;
                            return current.item;
                        }
                    }
                }
            }
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("The RandomQueue is empty.");
        else {
            int i = StdRandom.uniform(size) + 1;
            if (i > size / 2) {
                Node current = last;
                for (int j = 0; j < size - i; j++) {
                    current = current.prior;
                }
                return current.item;
            }
            else {
                Node current = first;
                for (int j = 0; j < i - 1; j++) {
                    current = current.next;
                }
                return current.item;
            }
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }

    public static void main(String[] args) {
        /*
        String filePath = "mediumTale.txt";
        File file = new File(filePath);
        // 判断文件是否存在
        if (file.isFile() && file.exists()) {
            // 创建一个文件输入流
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(filePath);
                InputStreamReader reader = new InputStreamReader(fin);
                BufferedReader buffReader = new BufferedReader(reader);
                String str;
                while ((str = buffReader.readLine()) != null) {
                    String[] s = str.split(" ");
                    RandomizedQueue<String> rq = new RandomizedQueue<String>();
                    for (int i = 0; i < s.length; i++) {
                        rq.enqueue(s[i]);
                        rq.sample();
                    }
                    StdOut.println(rq.isEmpty());
                    StdOut.println(rq.size());
                    Iterator<String> it = rq.iterator();
                    while (it.hasNext()) {
                        String j = it.next();
                        StdOut.printf("%s ", j);
                    }
                    StdOut.println();
                    for (String j: rq
                    ) {
                        StdOut.printf("%s ", j);
                    }
                    StdOut.println();
                    for (int k = 0; k < 5; k++) {
                        StdOut.printf("%s ", rq.sample());
                    }
                    StdOut.println();
                    for (int j = 0; j < s.length; j++) {
                        StdOut.printf("%s ", rq.dequeue());
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
