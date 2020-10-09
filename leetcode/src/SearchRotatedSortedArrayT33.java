public class SearchRotatedSortedArrayT33 {
    public int search(int[] nums, int target) {
        int bottom = findBottom(nums, 0, nums.length - 1);
        if (bottom != -1) {
            if (target > nums[0]) return findIdx(nums, 0, bottom - 1, target);
            else if (target < nums[0]) return findIdx(nums, bottom, nums.length - 1, target);
            else return 0;
        } else return findIdx(nums, 0, nums.length - 1, target);
    }

    public int findBottom(int[] nums, int lo, int hi) {
        if (nums[lo] <= nums[hi]) return -1;

        int mid = lo + (hi - lo) / 2;
        if (nums[mid] > nums[mid + 1]) return mid + 1;
        else return nums[mid] > nums[hi] ?
                findBottom(nums, mid + 1, hi) :
                findBottom(nums, lo, mid);
    }

    public int findIdx(int[] nums, int lo, int hi, int target) {
        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] > target) return findIdx(nums, lo, mid - 1, target);
        else return findIdx(nums, mid + 1, hi, target);
    }
}
