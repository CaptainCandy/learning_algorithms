public class MinimumSizeSubarraySumT209 {
	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length == 0 || nums == null) {
			return 0;
		}
		int start = 0, end = 0;
		int min = nums.length + 1;
		int sum = 0;
		while (end < nums.length) {
			sum += nums[end];
			while (sum >= s) {
				min = Math.min(min, end - start + 1);
				sum -= nums[start];
				start++;
			}
			end++;
		}

		return (min == nums.length + 1) ? 0: min;
	}
}
