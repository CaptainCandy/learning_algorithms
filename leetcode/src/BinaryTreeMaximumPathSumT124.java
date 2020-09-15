import java.lang.Math;

public class BinaryTreeMaximumPathSumT124 {
    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftmax = Math.max(dfs(node.left), 0);
        int rightmax = Math.max(dfs(node.right), 0);

        int thisnode = node.val + leftmax + rightmax;

        if (thisnode > max) max = thisnode;

        return Math.max(node.val + leftmax, node.val + rightmax);
    }
}
