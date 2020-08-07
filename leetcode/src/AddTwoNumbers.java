
//		输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//		输出：7 -> 0 -> 8
//		原因：342 + 465 = 807

public class AddTwoNumbers {
	private class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode p = l1, q = l2, cur = dummy;
		int carry = 0;
		while (p != null || q != null) {
			int x = p !=null ? p.val : 0;
			int y = q !=null ? q.val : 0;
			cur.next = new ListNode((x + y + carry) % 10);
			cur = cur.next;
			carry = (x + y + carry) / 10;
			if (p != null) p = p.next;
			if (q != null) q = q.next;
		}
		if (carry != 0) cur.next = new ListNode(1);

		return dummy.next;
	}
}
