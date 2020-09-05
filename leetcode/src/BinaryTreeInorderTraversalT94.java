import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversalT94 {
//	LinkedList<Integer> path = new LinkedList<Integer>();
	public List<Integer> inorderTraversal(TreeNode root) {
//		if (root != null) {
//			inorderTraversal(root.left);
//			path.add(root.val);
//			inorderTraversal(root.right);
//		}
		List<Integer> path = new ArrayList<Integer>();
		if (root == null) return path;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode cur = root;
		while (cur != null || !stack.empty()) {
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			path.add(cur.val);
			cur = cur.right;
		}
		return path;
	}
}
