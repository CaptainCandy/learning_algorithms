import java.util.HashMap;

public class BinaryTreeCamerasT968 {
    // 放摄像头
    HashMap<TreeNode, Integer> f = new HashMap<>();
    // 不放摄像头，但被监控
    HashMap<TreeNode, Integer> g = new HashMap<>();
    // 不被监控
    HashMap<TreeNode, Integer> h = new HashMap<>();

    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;

        int[] array = dfs1(root);
        return array[1];

//        dfs(root);
//        return Math.min(f.get(root), g.get(root));
    }

    private int[] dfs1(TreeNode node) {
        if (node == null) return new int[]{Integer.MAX_VALUE / 2, 0, 0};

        int[] array = new int[3];
        int[] left = dfs1(node.left);
        int[] right = dfs1(node.right);

        array[0] = 1 + left[2] + right[2];
        array[1] = Math.min(array[0], Math.min(left[0] + right[1], left[1] + right[0]));
        array[2] = Math.min(array[0], left[1] + right[1]);

        return array;
    }

    private void dfs2(TreeNode node) {
        if (node == null) return;

        dfs2(node.left);
        dfs2(node.right);


    }
}
