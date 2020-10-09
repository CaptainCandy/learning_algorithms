//Given a text, return the Huffman coding table of all the characters (including space if it has).
//        For example, given "this is an example of a huffman tree", return the table:
//        111
//        a 101
//        e 011
//        f 1101
//        h 1000
//        i 0000
//        l 00100
//        m 0101
//        n 0011
//        o 00011
//        p 00101
//        r 10011
//        s 1100
//        t 0100
//        u 00010
//        x 10010
//        Huffman Tree:
//        * Create a leaf node for each symbol and add it to the priority queue.
//        * While there is more than one node in the queue:
//
//        * Remove the two nodes of highest priority (lowest probability) from the queue
//        * Create a new internal node with these two nodes as children and with probability equal to the sum of the two nodes' probabilities.
//        * Add the new node to the queue.
//
//        * The remaining node is the root node and the tree is complete

import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    String val;
    int frequence = 0;
    HuffmanNode parent;
    HuffmanNode left;
    HuffmanNode right;
    HuffmanNode(String x) { val = x; }

    @Override
    public int compareTo(HuffmanNode o) {
        return frequence - o.frequence;
    }
}

public class HuffmanCoding {
    private Map<Character, Integer> stat;
    private List<HuffmanNode> leaves;
    private HuffmanNode root;
    private Map<Character, String> encodeResult;

    public HuffmanCoding(String s) {
        setStat(s);
        root = buildHuffmanTree(stat);
        encode();
    }

    public void check() {
        for (Character c: stat.keySet()
        ) {
            System.out.println(String.format("%s:%s", c, stat.get(c)));
        }
    }

    public void printCoding() {
        for (Character c: encodeResult.keySet()
             ) {
            System.out.println(String.format("%s:%s", c, encodeResult.get(c)));
        }
    }

    private void encode() {
        encodeResult = new HashMap<>();
        for (HuffmanNode curnode: leaves
             ) {
            HuffmanNode node = curnode;
            Character c = node.val.charAt(0);
            String code = "";
            while (node.parent != null) {
                if (node == node.parent.left) code = "0" + code;
                else code = "1" + code;
                node = node.parent;
            }
            encodeResult.put(c, code);
        }
    }

    private HuffmanNode buildHuffmanTree(Map<Character, Integer> stat) {
        leaves = new ArrayList<>();
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        for (Character key: stat.keySet()) {
            HuffmanNode node = new HuffmanNode(key.toString());
            node.frequence = stat.get(key);
            queue.add(node);
            leaves.add(node);
        }

        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            HuffmanNode n1 = queue.poll();
            HuffmanNode n2 = queue.poll();

            HuffmanNode n3 = new HuffmanNode(n1.val + n2.val);
            n3.frequence = n1.frequence + n2.frequence;
//            System.out.println(String.format("%s+%s=%s", n1.frequence, n2.frequence, n2.frequence));

            n3.left = n1;
            n3.right = n2;
            n1.parent = n3;
            n2.parent = n3;

            queue.offer(n3);
        }

        return queue.poll();
    }

    private void setStat(String s) {
        this.stat = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stat.containsKey(c)) stat.put(c, stat.get(c) + 1);
            else stat.put(c, 1);
        }
    }
}