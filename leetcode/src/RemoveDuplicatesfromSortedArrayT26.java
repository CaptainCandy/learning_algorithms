public class RemoveDuplicatesfromSortedArrayT26 {
	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return 1;
		int p = 1, q = 0;
		while (p < nums.length) {
			if (nums[p] != nums[q]) {
				q++;
				nums[q] = nums[p];
			}
			p++;
		}

		return q + 1;
	}
}
