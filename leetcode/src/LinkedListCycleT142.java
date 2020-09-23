import java.util.List;

public class LinkedListCycleT142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode inter = findInter(head);
        if (inter == null) return null;

        ListNode p = head;
        ListNode q = inter;
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    public ListNode findInter(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return slow;
        }
        return null;
    }
}
