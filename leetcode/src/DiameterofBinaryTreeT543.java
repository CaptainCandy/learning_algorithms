public class DiameterofBinaryTreeT543 {
    int res;

    public int diameterOfBinaryTree(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftmax = dfs(node.left);
        int rightmax = dfs(node.right);

        res = Math.max(res, leftmax + rightmax + 1);
        return Math.max(leftmax, rightmax);
    }
}
