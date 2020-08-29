import jdk.management.resource.internal.ApproverGroup;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PerfectSquaresT279 {
	private int[] squares;
	private int target;
	private int max_square;

	public int numSquares(int n) {
		target = n;
		max_square = (int) Math.floor(Math.sqrt(n));
		if (Math.pow(max_square, 2) == n) return 1;

		squares = new int[max_square];
		for (int i = 0; i < max_square; i++) {
			squares[i] = (int) Math.pow(i + 1, 2);
		}
		int res = 0;

		Queue<Integer> nodes = new LinkedList<Integer>();
		Queue<Integer> sums = new LinkedList<Integer>();
		Queue<Integer> steps = new LinkedList<Integer>();
		for (int i = 0; i < max_square; i++) {
			nodes.add(squares[i]);
			sums.add(squares[i]);
			steps.add(1);
		}
		while (!nodes.isEmpty()) {
			int node = nodes.poll();
			int sum = sums.poll();
			int step = steps.poll();
			boolean flag = false;
			for (int s: squares
				 ) {
				if (s <= node) {
					if (sum + s == target) {
						res = step + 1;
						flag = true;
						break;
					}
					else if (sum + s > target) {
						break;
					}
					else {
						System.out.println(String.format("%s,%s,%s", s,sum+s,step+1));
						nodes.add(s);
						sums.add(sum + s);
						steps.add(step + 1);
					}
				}
			}
			if (flag) break;
		}
		return res;
	}
}
