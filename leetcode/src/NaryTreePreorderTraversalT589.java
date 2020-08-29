import java.util.ArrayList;
import java.util.List;

class Node {
	public int val;
	public List<Node> children;

	public Node() {}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
}

public class NaryTreePreorderTraversalT589 {
	List<Integer> path = new ArrayList<Integer>();
	public List<Integer> preorder(Node root) {
		if (root != null) {
			path.add(root.val);
			for (Node child: root.children
				 ) {
				preorder(child);
			}
		}
		return path;
	}
}
