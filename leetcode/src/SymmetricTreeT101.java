public class SymmetricTreeT101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkSym(root.left, root.right);
    }

    public boolean checkSym(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        return left.val == right.val
                && checkSym(left.left, right.right)
                && checkSym(left.right, right.left);
    }
}
