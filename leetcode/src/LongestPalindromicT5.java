public class LongestPalindromicT5 {
	public String longestPalindrome(String s) {
		int n = s.length();
		boolean[][]	dp = new boolean[n][n];
		String res = "";

		for (int l = 0; l < n; l++) {
			for (int i = 0; i < n - l; i++) {
				int j = i + l;
				if (l == 0)	{
					dp[i][j] = true;
				}
				else if (l == 1) {
					dp[i][j] = s.charAt(i) == s.charAt(j);
				}
				else {
					dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
				}
				if (dp[i][j] && l + 1 > res.length()) {
					res = s.substring(i, j);
				}
			}
		}

		return res;
	}
}
