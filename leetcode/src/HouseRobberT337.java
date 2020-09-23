import java.util.*;

public class HouseRobberT337 {
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));

//        List<Integer> nums = new ArrayList<Integer>();
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            nums.add(node.val);
//            System.out.println();
//            if (node.val != 0 && (node.left != null || node.right != null)) {
//                queue.add(node.left == null ? new TreeNode(0) : node.left);
//                queue.add(node.right == null ? new TreeNode(0) : node.right);
//            }
//        }
//
//        int[] dp = new int[nums.size()];
//        dp[0] = nums.get(0);
//        dp[1] = nums.get(1);
//        dp[2] = nums.get(2);
//        for (int i = 3; i < nums.size(); i++) {
//            dp[i] = Math.max(dp[(i - 1) / 2], nums.get(i) + dp[(((i - 1) / 2) - 1) / 2]);
//        }
//        return dp[nums.size() - 1];
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        dfs(node.right);

        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(g.getOrDefault(node.left, 0), f.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
}
