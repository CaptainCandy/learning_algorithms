/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double[] threshold;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) throw new IllegalArgumentException();
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation per = new Percolation(n);
            while (!per.percolates()) {
                int site = StdRandom.uniform(n*n) + 1;
                int col = site % n;
                // 每一行的最后一个要特殊处理
                if (col == 0) col = n;
                int row = (site - col) / n + 1;
                if (!per.isOpen(row, col)) {
                    per.open(row, col);
                }
            }
            threshold[i] = (double) per.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
        // double sum = 0;
        // for (int i = 0; i < threshold.length; i++) {
        //     sum += threshold[i];
        // }
        // return sum/threshold.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
        // double mean = mean();
        // double sum = 0;
        // for (int i = 0; i < threshold.length; i++) {
        //     sum += Math.pow(threshold[i] - mean, 2);
        // }
        // return Math.pow(sum/(threshold.length - 1), 1/2);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(threshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(threshold.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        PercolationStats pers = new PercolationStats(2, 100);

        System.out.println(sw.elapsedTime());
        System.out.printf("mean = %f%nstddev = %f%n95%% confidence interval = [%f, %f]",
                          pers.mean(), pers.stddev(),
                          pers.confidenceLo(), pers.confidenceHi());
    }
}