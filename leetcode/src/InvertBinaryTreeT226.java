import java.util.ArrayDeque;
import java.util.Queue;

public class InvertBinaryTreeT226 {
    public TreeNode invertTree(TreeNode root) {
        bfs(root);
        return root;
    }

    private void bfs(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode x = queue.poll();
            TreeNode tmp = x.left;
            x.left = x.right;
            x.right = tmp;
            if (x.left != null) queue.add(x.left);
            if (x.right != null) queue.add(x.right);
        }
    }
}
