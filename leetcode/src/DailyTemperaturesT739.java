import java.util.Stack;

public class DailyTemperaturesT739 {
	public int[] dailyTemperatures(int[] T) {
		int n = T.length;
		if (n == 0) return new int[0];
		int[] diff = new int[n];
		Stack<Integer> index = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			if (index.empty()) index.push(i);
			else {
				while (!index.empty() && T[index.peek()] < T[i]) {
					int t = index.pop();
					diff[t] = i - t;
				}
				index.push(i);
			}
		}
		return diff;
	}
}
