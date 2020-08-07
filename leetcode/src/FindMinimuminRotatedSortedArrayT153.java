public class FindMinimuminRotatedSortedArrayT153 {
	public int findMin(int[] nums) {
		int n = nums.length;
		if (n == 1) return nums[0];
		if (n == 2) return Math.min(nums[0], nums[1]);
		if (nums[0] < nums[n - 1]) return nums[0];

		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;

			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}

			if (nums[mid] < nums[mid - 1]) {
				return nums[mid];
			}

			if (nums[mid] < nums[0]) {
				hi = mid - 1;
			}
			else {
				lo = mid + 1;
			}
		}

		return -1;
	}
}
