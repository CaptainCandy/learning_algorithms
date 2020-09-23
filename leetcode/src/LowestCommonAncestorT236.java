import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestorT236 {
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        dfs(root);
//        while (p != null) {
//            visited.add(p.val);
//            p = parent.get(p.val);
//        }
//        while (q != null) {
//            if (visited.contains(q.val)) return q;
//            q = parent.get(q.val);
//        }
//        return root;

        dfs(root, p, q);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);

        if ((left && right) ||
                ((root.val == p.val || root.val == q.val) && (left || right))) {
            res = root;
        }

        return (root.val == p.val || root.val == q.val) || left || right;
    }
}
