public class ReverseLinkedListT206 {
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
