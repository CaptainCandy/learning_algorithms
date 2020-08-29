import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversalT144 {
	List<Integer> path = new ArrayList<Integer>();
	public List<Integer> preorderTraversal(TreeNode root) {
//		if (root != null) {
//			path.add(root.val);
//			preorderTraversal(root.left);
//			preorderTraversal(root.right);
//		}
//		return path;
		if (root == null) return path;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			path.add(node.val);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return path;
	}
}
