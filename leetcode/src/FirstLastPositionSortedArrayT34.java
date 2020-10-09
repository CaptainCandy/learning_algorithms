public class FirstLastPositionSortedArrayT34 {
    public int[] searchRange(int[] nums, int target) {
        int left = findLeft(nums, target);
        if (left == -1) return new int[]{-1, -1};
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    public int findLeft(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (lo != nums.length && nums[lo] == target) {
            return lo;
        }
        return -1;
    }

    public int findRight(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return hi;
    }
}
