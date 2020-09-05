import java.util.HashMap;

public class ConstructBinaryTreefromInorderPostorderT106 {
    public int rootIndex;
    public int[] inorder;
    public int[] postorder;
    public HashMap<Integer, Integer> idx = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) return null;
        this.rootIndex = postorder.length - 1;
        this.inorder = inorder;
        this.postorder = postorder;

        for (int i = 0; i < inorder.length; i++) {
            idx.put(inorder[i], i);
        }

        return buildSubTree(0, inorder.length - 1);
    }

    public TreeNode buildSubTree(int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int rootVal = postorder[rootIndex];
        TreeNode root = new TreeNode(rootVal);

        rootIndex--;

        Integer index = idx.get(rootVal);
        root.right = buildSubTree(index + 1, hi);
        root.left = buildSubTree(lo, index - 1);

        return root;
    }
}