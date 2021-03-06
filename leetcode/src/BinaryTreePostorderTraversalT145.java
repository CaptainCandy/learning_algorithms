import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversalT145 {
//	LinkedList<Integer> path = new LinkedList<Integer>();
	public List<Integer> postorderTraversal(TreeNode root) {
//		if (root != null) {
//			postorderTraversal(root.left);
//			postorderTraversal(root.right);
//			path.add(root.val);
//		}
		LinkedList<Integer> path = new LinkedList<Integer>();
		if (root == null) return path;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			path.addFirst(node.val);
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {
				stack.push(node.right);
			}
		}
		return path;
	}
}
