import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class DiagonalTraverseT498 {
	public int[] findDiagonalOrder(int[][] matrix) {

		if (matrix == null || matrix.length == 0) {
			return new int[0];
		}

		int rownum = matrix.length;
		int colnum = matrix[0].length;
		int[] res = new int[rownum * colnum];
		int d = 0;

		for (int k = 0; k < rownum * colnum; k++) {
			int r = k < colnum ? 0 : k + 1 - colnum;
			int c = k < colnum ? k : colnum - 1;

			ArrayList<Integer> local = new ArrayList<Integer>();
			while (r < rownum && c >= 0) {
				local.add(matrix[r][c]);
				r++;
				c--;
			}

			if (k % 2 == 0) {
				Collections.reverse(local);
			}

			for (int i = 0; i < local.size(); i++) {
				res[d++] = local.get(i);
			}
		}

		return res;
	}
}
