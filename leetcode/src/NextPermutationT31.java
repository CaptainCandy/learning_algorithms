public class NextPermutationT31 {
	public void nextPermutation(int[] nums) {
		int i;
		int n = nums.length;
		for (i = n - 1; i > 0; i--) {
			if (nums[i] > nums[i - 1]) break;
		}
		if (i == 0) reverse(0, n - 1, nums);
		else {
			for (int j = n - 1; j >= i; j--) {
				if (nums[j] > nums[i - 1]) {
					swap(i - 1, j, nums);
					reverse(i, n - 1, nums);
					break;
				}
			}
		}
	}

	public void reverse(int i, int j, int[] nums) {
		while (i < j) {
			swap(i, j, nums);
			i++;
			j--;
		}
	}

	public void swap(int i, int j, int[] nums) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3};
		NextPermutationT31 next = new NextPermutationT31();
		next.nextPermutation(nums);
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i]);
		}
	}
}