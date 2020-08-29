public class ValidateBinarySearchTreeT98 {
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		return recursion(root, null, null);
	}

	public boolean recursion(TreeNode child, Integer low, Integer high) {
		// terminator
		if (child == null) return true;
		if (low != null && child.val <= low) return false;
		if (high != null && child.val >= high) return false;

		// process

		//drill down
		if (!recursion(child.left, low, child.val)) return false;
		if (!recursion(child.right, child.val, high)) return false;
		return true;
	}
}
