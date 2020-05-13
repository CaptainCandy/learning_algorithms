/* *****************************************************************************
 *  Name: Xinze Tang
 *  Date: 2020-05-12
 *  Description: use doubly linked list
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // inner class node 16+8*2+8+8=48 bytes memory
    private class Node {
        Item item;
        Node next;
        Node prior;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
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

    // construct an empty deque
    public Deque() { }

    // is the deque empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException("A null item detected."); }
        else if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
            size++;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prior = first;
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException("A null item detected."); }
        else if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
            size++;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prior = oldLast;
            oldLast.next = last;
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (!isEmpty()) {
            Item item = first.item;
            first = first.next;
            size--;
            return item;
        }
        else {
            throw new NoSuchElementException("The deque is empty.");
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (!isEmpty()) {
            Item item = last.item;
            last = last.prior;
            size--;
            return item;
        }
        else {
            throw new NoSuchElementException("The deque is empty.");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new ListIterator(); }

    // unit testing (required)
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
                    Deque<String> de = new Deque<String>();
                    for (int i = 0; i < s.length; i++) {
                        de.addFirst(s[i]);
                        de.addLast(s[i]);
                    }
                    StdOut.println(de.isEmpty());
                    StdOut.println(de.size());
                    Iterator<String> it = de.iterator();
                    while (it.hasNext()) {
                        String j = it.next();
                        StdOut.printf("%s ", j);
                    }
                    for (String j: de
                         ) {
                        StdOut.printf("%s ", j);
                    }
                    for (int j = 0; j < s.length; j++) {
                        StdOut.println(de.removeLast());
                        StdOut.println(de.removeFirst());
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}