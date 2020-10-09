import java.util.*;

public class HuffmanCoder {
    private Map<Character, String> dict;

    public HuffmanCoder(Map<Character, Integer> frequencyMap) {
        PriorityQueue<CodingNode> freeNodes = new PriorityQueue<>(
                frequencyMap.size(),
                Comparator.comparing(t -> t.freq)
        );
        frequencyMap.forEach((k,v) -> {
            freeNodes.add(new CodingNode(v, k));
        });
        while (freeNodes.size() > 1) {
            CodingNode a = freeNodes.poll();
            CodingNode b = freeNodes.poll();
            CodingNode combined = new CodingNode(a, b);
            freeNodes.add(combined);
        }
        dict = new HashMap<>(frequencyMap.size());
        dfs(freeNodes.peek(), "");
    }

    private void dfs(CodingNode root, String code) {
        if (root == null) return;
        if (root.isLeaf()) {
            this.dict.put(root.word, code);
            return;
        }
        dfs(root.left, code + "1");
        dfs(root.right, code + "0");
    }

    static void test() {
        HuffmanCoder coder = new HuffmanCoder(new HashMap<Character, Integer>(){
            {
                put('a', 100);
                put('b', 200);
                put('c', 150);
            }
        });
    }
}

class CodingNode {
    int freq;
    char word;
    CodingNode left, right;
    public CodingNode(int freq, char word) {
        this.freq = freq;
        this.word = word;
    }
    public CodingNode(CodingNode left, CodingNode right) {
        this.left = left;
        this.right = right;
        this.freq = left.freq + right.freq;
    }
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
