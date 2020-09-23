import java.util.HashMap;
import java.util.Map;

class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class DeepCopyO35 {
    public Node1 copyRandomList(Node1 head) {
        Map<Node1, Node1> map = new HashMap<>();
        Node1 cur = head;
        while (cur != null) {
            map.put(cur, new Node1(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }
}
