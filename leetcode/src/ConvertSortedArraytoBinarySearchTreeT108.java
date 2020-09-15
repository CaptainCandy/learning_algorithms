public class ConvertSortedArraytoBinarySearchTreeT108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return findRoot(nums, 0, nums.length - 1);
    }

    public TreeNode findRoot(int[] nums, int low, int high) {
        if (low > high) return null;

        int mid = (low + high) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = findRoot(nums, low, mid - 1);
        root.right = findRoot(nums, mid + 1, high);
        return root;
    }
}
