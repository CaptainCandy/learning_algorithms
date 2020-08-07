import java.util.*;

public class MergekSortedListsT23 {
	private class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int x) { val = x; }
	}

	private class PointList {
		List<ListNode> points;
		boolean[] isNull;
		int notNullSize;

		public PointList(int n) {
			points = new ArrayList<ListNode>(n);
			isNull = new boolean[n];
			notNullSize = n;
		}

		public void moveNext(int idx) {
			ListNode m = points.get(idx);
			points.set(idx, m.next);
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {
//		ListNode dummy = new ListNode(0);
//		ListNode r = dummy;
//		int n = lists.length;
//		PointList p = new PointList(n);
//		for (int i = 0; i < n; i++) {
//			p.points.add(lists[i]);
//		}
//		while (true){
//			if (p.notNullSize == 1) {
//				for (int j = 0; j < n; j++) {
//					if (!p.isNull[j]) {
//						r.next = p.points.get(j);
//						break;
//					}
//				}
//				break;
//			}
//			else {
//				int min = -1;
//				int idx = 0;
//				for (int k = 0; k < n; k++) {
//					if (p.isNull[k] == false && p.points.get(k) == null) {
//						p.isNull[k] = true;
//						p.notNullSize --;
//					}
//					else if (p.isNull[k] == false) {
//						if (min == -1) {
//							min = p.points.get(k).val;
//							idx = k;
//							p.moveNext(k);
//						}
//						else {
//							int val = p.points.get(k).val;
//							if (val < min) {
//								min = val;
//								idx = k;
//							}
//						}
//					}
//				}
//				r.next = p.points.get(idx);
//				r = r.next;
//				p.moveNext(idx);
//			}
//		}
//
//		return dummy.next;
		ListNode res = null;
		for (int i = 0; i < lists.length; i++) {
			res = mergeTwoLists(res, lists[i]);
		}
		return res;
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
