public class ClimbingStairsT70 {
	public int climbStairs(int n) {
//		递归解法 超时
//		if (n == 1) return 1;
//		if (n == 2) return 2;
//		return climbStairs(n - 1) + climbStairs(n - 2) + 2;

		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			if (i < 2) dp[i] = i + 1;
			else dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n - 1];
	}
}
