/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    /** constant number. */
    private static final double STDCONST = 1.96;
    /** records each trial's p value. */
    private final double[] thresholds;

    /**
     * perform independent trials on an n-by-n grid.
     *
     * @param n      grid size
     * @param trials number of trials
     */
    public PercolationStats(final int n, final int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            // initialize all sites (to be blocked)
            Percolation percolation = new Percolation(n);
            int count = 0;
            // keep opening the site until the grids percolate
            while (!percolation.percolates()) {
                // uniformly random pick a site
                int targetRow = StdRandom.uniform(n) + 1;
                int targetCol = StdRandom.uniform(n) + 1;
                // opens the site
                while (percolation.isOpen(targetRow, targetCol)) {
                    targetRow = StdRandom.uniform(n) + 1;
                    targetCol = StdRandom.uniform(n) + 1;
                }
                percolation.open(targetRow, targetCol);
                count++;
            }
            // calculates p rate
            double threshold = count / (double) (n * n);
            // update the thresholds
            thresholds[i] = threshold;
        }
    }

    /**
     * sample mean of percolation threshold.
     *
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * calculates sample standard deviation of percolation threshold.
     *
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * calculates low endpoint of 95% confidence interval.
     *
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean()
                - (STDCONST * Math.sqrt(stddev()) / Math.sqrt(thresholds.length));
    }

    /**
     * calculates high endpoint of 95% confidence interval.
     *
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean()
                + (STDCONST * Math.sqrt(stddev()) / Math.sqrt(thresholds.length));
    }

    /**
     * main function.
     *
     * @param args input
     */
    public static void main(final String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.printf("mean                    = %f%n",
                      percolationStats.mean());
        StdOut.printf("stddev                  = %f%n",
                      percolationStats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]%n",
                      percolationStats.confidenceLo(),
                      percolationStats.confidenceHi());
    }
}
