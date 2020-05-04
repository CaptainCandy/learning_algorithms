/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import java.io.*;

public class Percolation {
    private boolean[][] grid;
    private final WeightedQuickUnionUF wqu;
    private final WeightedQuickUnionUF wquTop;
    private int countOpen = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Illegal argument.");
        // initialized grid, 0 = blocked, 1 = open
        grid = new boolean[n][n];
        // plus 2 means the 2 vitual sites beyond the top line and
        // under the bottom line
        wqu = new WeightedQuickUnionUF(n*n + 2);
        wquTop = new WeightedQuickUnionUF(n*n + 1);
        for (int i = 0; i < n; i++) {
            wqu.union(0, map(1, i+1));
            wqu.union(n*n+1, map(n, i+1));
            wquTop.union(0, map(1, i+1));
        }
    }
    // map 2d array to 1d
    private int map(int row, int col) {
        return grid.length * (row - 1) + col;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid.length)
            throw new IllegalArgumentException("Index out of bounds. Please check.");
        if (!grid[row-1][col-1]) {
            grid[row-1][col-1] = true;
            countOpen++;

            if (row != 1 && isOpen(row-1, col)) {
                wqu.union(map(row, col), map(row - 1, col));
                wquTop.union(map(row, col), map(row - 1, col));
            }

            if (col != 1 && isOpen(row, col-1)) {
                wqu.union(map(row, col), map(row, col - 1));
                wquTop.union(map(row, col), map(row, col - 1));
            }

            if (row != grid.length && isOpen(row+1, col)) {
                wqu.union(map(row, col), map(row + 1, col));
                wquTop.union(map(row, col), map(row + 1, col));
            }

            if (col != grid.length && isOpen(row, col+1)) {
                wqu.union(map(row, col), map(row, col + 1));
                wquTop.union(map(row, col), map(row, col + 1));
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid.length)
            throw new IllegalArgumentException("Index out of bounds. Please check.");
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (grid.length == 1) {
            return isOpen(1, 1);
        }
        return isOpen(row, col) && wquTop.connected(0, map(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return countOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grid.length == 1) {
            return isOpen(1, 1);
        }
        return wqu.connected(grid.length*grid.length+1, 0);
    }

    // test client (optional)
    public static void main(String[] args) {
        /*
        String filePath = "input10.txt";
        File file = new File(filePath);
        Percolation per;
        // 判断文件是否存在
        if (file.isFile() && file.exists()) {
            // 创建一个文件输入流
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(filePath);
                InputStreamReader reader = new InputStreamReader(fin);
                BufferedReader buffReader = new BufferedReader(reader);
                int n = Integer.parseInt(buffReader.readLine());
                per = new Percolation(n);
                String str = "";
                while ((str = buffReader.readLine()) != null) {
                    // \\s+表示匹配多个空格
                    String co[] = str.split("\\s+");
                    int row = Integer.parseInt(co[1]);
                    int col = Integer.parseInt(co[2]);
                    per.open(row, col);
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.printf("%b ", per.grid[i][j]);
                    }
                    System.out.print("\n");
                }
                System.out.print("\n");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.printf("%b ", per.isFull(i+1, j+1));
                    }
                    System.out.print("\n");
                }
                System.out.println(per.percolates());
                System.out.println(per.countOpen);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}