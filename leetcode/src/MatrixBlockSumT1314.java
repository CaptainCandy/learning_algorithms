public class MatrixBlockSumT1314 {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int[][] resMat = new int[mat.length][mat[0].length];

        // 暴力法
//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[0].length; j++) {
//                int res = 0;
//                for (int k = (i - K > 0 ? i - K : 0); k <= (i + K < mat.length ? i + K : mat.length - 1); k++) {
//                    for (int l = (j - K > 0 ? j - K : 0); l <= (j + K < mat[0].length ? j + K : mat[0].length - 1); l++) {
//                        res += mat[k][l];
//                    }
//                }
//                resMat[i][j] = res;
//            }
//        }
//        return resMat;

        // 前缀和数组
        int[][] P = new int[mat.length + 1][mat[0].length + 1];
//        for (int i = 0; i < mat.length + 1; i++) {
//            P[i][0] = 0;
//        }
//        for (int i = 0; i < mat[0].length + 1; i++) {
//            P[0][i] = 0;
//        }
        for (int i = 1; i < mat.length + 1; i++) {
            for (int j = 1; j < mat[0].length + 1; j++) {
                P[i][j] = mat[i - 1][j - 1] + P[i - 1][j] + P[i][j - 1] - P[i - 1][j - 1];
            }
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int r1 = Math.max(i - K, 0);
                int r2 = Math.min(i + K, mat.length - 1);
                int c1 = Math.max(j - K, 0);
                int c2 = Math.min(j + K, mat[0].length - 1);
                resMat[i][j] = P[r2 + 1][c2 + 1] - P[r1][c2 + 1] - P[r2 + 1][c1] + P[r1][c1];
            }
        }
        return resMat;
    }
}
