import java.util.*;

public class WallsandGatesT286 {
	private final List<int[]> SURROUNDING = Arrays.asList(
			new int[] {-1, 0},
			new int[] {1, 0},
			new int[] {0, -1},
			new int[] {0, 1}
	);

	public void wallsAndGates(int[][] rooms) {
		int m = rooms.length;
		if (m == 0) return;
		int n = rooms[0].length;
		Queue<int[]> queue = new LinkedList<int[]>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {
					queue.add(new int[]{i, j});
				}
			}
		}
		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			for (int[] s: SURROUNDING
				 ) {
				int row = node[0] + s[0];
				int col = node[1] + s[1];
				if (row < 0 || row > m - 1 || col < 0 || col > n - 1 || rooms[row][col] != Integer.MAX_VALUE) {
					continue;
				}
				rooms[row][col] = rooms[node[0]][node[1]] + 1;
				queue.add(new int[]{row, col});
			}
		}
	}
}
