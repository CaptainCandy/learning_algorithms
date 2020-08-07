import java.util.HashMap;

public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int y = target - nums[i];
			if (hash.containsKey(y)) {
				return new int[] {hash.get(y), i};
			}
			else {
				hash.put(nums[i], i);
			}
		}
		return null;
	}
}
