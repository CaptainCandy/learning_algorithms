import java.util.ArrayList;
import java.util.List;

public class PascalTriangleT118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0) return res;
        res.add(new ArrayList<Integer>(){{add(1);}});
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            List<Integer> pre = res.get(i - 1);
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(pre.get(j) + pre.get(j - 1));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }
}
