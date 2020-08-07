public class MergeTwoSortedListsT21 {
	private class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode r = dummy;
		while (true) {
			if (l1 == null) {
				r.next = l2;
				break;
			}
			else if (l2 == null) {
				r.next = l1;
				break;
			}
			else {
				if (l1.val > l2.val) {
					r.next = l2;
					r = r.next;
					l2 = l2.next;
				} else {
					r.next = l1;
					r = r.next;
					l1 = l1.next;
				}
			}
		}

		return dummy.next;
	}
}
