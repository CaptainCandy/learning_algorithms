import java.util.HashMap;

public class ConstructBinaryTreefromInorderPreorderT105 {
    public int rootIndex;
    public int[] inorder;
    public int[] preorder;
    public HashMap<Integer, Integer> idx = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || preorder == null || inorder.length == 0 || preorder.length == 0) return null;
        this.rootIndex = 0;
        this.inorder = inorder;
        this.preorder = preorder;

        for (int i = 0; i < inorder.length; i++) {
            idx.put(inorder[i], i);
        }

        return buildSubTree(0, inorder.length - 1);
    }

    public TreeNode buildSubTree(int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int rootVal = preorder[rootIndex];
        TreeNode root = new TreeNode(rootVal);

        rootIndex++;

        Integer index = idx.get(rootVal);
        root.left = buildSubTree(lo, index - 1);
        root.right = buildSubTree(index + 1, hi);

        return root;
    }
}
