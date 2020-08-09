public class LinkedListCycleT141 {
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
		ListNode p = head;
		head = reverseList(head);
		if (head.equals(p)) return true;
		return false;
	}

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			head = cur;
			cur = cur.next;
			head.next = pre;
			pre = head;
		}

		return head;
	}
}