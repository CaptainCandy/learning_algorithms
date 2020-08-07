import java.util.Stack;

public class LongestValidParenthesesT32 {
	public int longestValidParentheses(String s) {
		int n = s.length();
		int[] dp = new int[n];
		if  (n == 0 || n == 1) return 0;
		dp[0] = 0;
		int max = 0;

		if (s.charAt(0) == '(' && s.charAt(1) == ')') {
			max = 2;
			dp[1] = 2;
		}
		else {
			dp[1] = 0;
		}
		for (int i = 2; i < n; i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = dp[i - 2] + 2;
				}
				else if (i - dp[i - 1] >= 2 && s.charAt(i - dp[i - 1] - 1) == '('){

					dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
				}
				else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
					dp[i] = dp[i - 1] + 2;
				}
				max = Math.max(max, dp[i]);
			}
		}

		return max;
	}
}
