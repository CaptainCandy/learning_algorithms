public class RotateImageT48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                move4(matrix, i, j);
            }
        }
    }

    public void move4(int[][] m, int row, int col) {
        int temp = m[row][col];
        for (int i = 0; i < 4; i++) {
            int t = row;
            row = col;
            col = m.length - 1 - t;
            int v = m[row][col];
            m[row][col] = temp;
            temp = v;
        }
    }
}
