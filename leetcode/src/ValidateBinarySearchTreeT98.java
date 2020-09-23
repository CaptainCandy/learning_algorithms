import java.util.Stack;

public class ValidateBinarySearchTreeT98 {
	public boolean isValidBST(TreeNode root) {
		if (root == null) return true;

		// 递归
//		return recursion(root, null, null);

		// 迭代
		double pivot = -Double.MAX_VALUE;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (!stack.empty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();

			if (pivot >= root.val) return false;
			pivot = root.val;
			root = root.right;
		}
		return true;
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
