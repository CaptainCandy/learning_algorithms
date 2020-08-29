public class MaximumDepthofBinaryTreeT104 {
	private int maxDepth;

	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		recursion(root, 1);
		return maxDepth;
	}

	public void recursion(TreeNode child, int depth) {
		// terminator
		if (child.left == null && child.right == null) {
			if (depth > maxDepth) maxDepth = depth;
		}

		// process

		//drill down
		if (child.left != null) recursion(child.left, depth + 1);
		if (child.right != null) recursion(child.right, depth + 1);
	}
}
