import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTreeT655 {
    public List<List<String>> printTree(TreeNode root) {
        int m = getHeight(root);
        int n = (int) Math.pow(2, m) - 1;

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                tmp.add("");
            }
            res.add(tmp);
        }

        fill(root, res, 0, 0, n - 1);

        return res;
    }

    private void fill(TreeNode node, List<List<String>> res, int row, int lo, int hi) {
        if (node == null) return;

        int mid = lo + (hi - lo) / 2;
        res.get(row).set(mid, Integer.toString(node.val));

        fill(node.left, res, row + 1, lo, mid - 1);
        fill(node.right, res, row + 1, mid + 1, hi);
    }

    private int findDepth(TreeNode node, int curDepth, int maxDepth) {
        if (node == null) return maxDepth;

        curDepth++;
        if (curDepth > maxDepth) maxDepth = curDepth;

        return Math.max(findDepth(node.left, curDepth, maxDepth), findDepth(node.right, curDepth, maxDepth));
    }

    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
