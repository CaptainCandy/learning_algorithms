public class MaximumSideLengthT1292 {
    public int maxSideLength(int[][] mat, int threshold) {
        int[][] P = new int[mat.length + 1][mat[0].length + 1];
        for (int i = 1; i < mat.length + 1; i++) {
            for (int j = 1; j < mat[0].length + 1; j++) {
                P[i][j] = mat[i - 1][j - 1] + P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1];
            }
        }

        int r = Math.min(mat.length, mat[0].length);
        int res = 0;
        for (int i = 1; i <= mat.length; i++) {
            for (int j = 1; j <= mat[0].length; j++) {
                for (int k = res + 1; k <= r; k++) {
                    if (i + k <= mat.length + 1 && j + k <= mat[0].length + 1 && getBlockSum(P, i, i + k - 1, j, j + k - 1) <= threshold)
                        res++;
                    else break;
                }
            }
        }
        return res;
    }

    private int getBlockSum(int[][] P, int r1, int r2, int c1, int c2) {
        return P[r2][c2] - P[r1 - 1][c2] - P[r2][c1 - 1] + P[r1 - 1][c1 - 1];
    }
}
