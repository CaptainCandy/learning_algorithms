public class RemoveNthNodeFromEndofListT19 {
	private class ListNode {
		int val;
      	ListNode next;
      	ListNode(int x) { val = x; }
 	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode p = dummy;
		ListNode q = dummy;

		for (int i = 0; i < n + 1; i++) {
			q = q.next;
		}

		while (q != null) {
			p = p.next;
			q = q.next;
		}
		if (p.next == head){
			p.next = p.next.next;
			return p.next;
		}
		else {
			p.next = p.next.next;
			return dummy.next;
		}
	}
}
