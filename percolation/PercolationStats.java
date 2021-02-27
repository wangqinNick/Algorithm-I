/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
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
                percolation.open(targetRow, targetCol);
                count++;
            }
            // calculates p rate
            double threshold = count / (double) (n * n);
            // update the thresholds
            thresholds[i] = threshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * Math.sqrt(stddev()) / Math.sqrt(thresholds.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * Math.sqrt(stddev()) / Math.sqrt(thresholds.length));
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int T = StdIn.readInt();
        
    }
}
