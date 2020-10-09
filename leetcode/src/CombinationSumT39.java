import java.util.ArrayList;
import java.util.List;

public class CombinationSumT39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, res, target, new ArrayList<Integer>(), 0);
        return res;
    }

    public void dfs(int[] candidates, List<List<Integer>> res, int target, List<Integer> l, int idx) {
        if (idx == candidates.length) return;
        if (target == 0) {
            res.add(new ArrayList<Integer>(l));
            return;
        }
        dfs(candidates, res, target, l, idx + 1);
        if (candidates[idx] <= target) {
            l.add(candidates[idx]);
            dfs(candidates, res, target - candidates[idx], l, idx);
            // 因为Java是值传递地址，l这个变量会一直在，所以当前选取操作做完之后，撤销操作，
            // 再做下一个可能的操作，不然答案里会出现很多重复值
            l.remove(l.size() - 1);
        }
    }
}
