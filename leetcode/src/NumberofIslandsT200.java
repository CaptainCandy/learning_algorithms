import java.util.LinkedList;
import java.util.Queue;

public class NumberofIslandsT200 {
	public int numIslands(char[][] grid) {
		int m = grid.length;
		if (m == 0) return 0;
		int n = grid[0].length;
		if (m == 1 && n == 1) return grid[0][0] - '0';

		int res = 0;
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (grid[r][c] == '1') {
					Queue<int[]> queue = new LinkedList<int[]>();
					queue.add(new int[]{r, c});
					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						int i = cur[0];
						int j = cur[1];
						if (i - 1 >= 0) {
							if (grid[i - 1][j] == '1') {
								queue.add(new int[]{i - 1, j});
								grid[i - 1][j] = '0';
							}
						}
						if (i + 1 < m) {
							if (grid[i + 1][j] == '1') {
								queue.add(new int[]{i + 1, j});
								grid[i + 1][j] = '0';
							}
						}
						if (j - 1 >= 0) {
							if (grid[i][j - 1] == '1') {
								queue.add(new int[]{i, j - 1});
								grid[i][j - 1] = '0';
							}
						}
						if (j + 1 < n) {
							if (grid[i][j + 1] == '1') {
								queue.add(new int[]{i, j + 1});
								grid[i][j + 1] = '0';
							}
						}
					}
					res++;
				}
			}
		}
		return res;
	}
}
