import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalT102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> path = new LinkedList<List<Integer>>();
        if (root == null) return path;
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode head = q.poll();
                level.add(head.val);
                if (head.left != null) q.offer(head.left);
                if (head.right != null) q.offer(head.right);
            }
            path.add(level);
        }
        return path;
    }
}
