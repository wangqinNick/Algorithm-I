/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF; // flatten 2D array
    private boolean[] isOpenGrids; // false blocked, true open
    private int size; // row / column size

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        // validation for the size
        if (n <= 0) throw new IllegalArgumentException("Invalid grids size");
        this.size = n;

        // creates the UF object
        // +2 special nodes (for checking Percolation)
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);

        // union the first row and size*size
        // union the last row and size*size + 1
        for (int i = 0; i < size; i++) {
            weightedQuickUnionUF.union(i, size * size);
            weightedQuickUnionUF.union(size * (size - 1) + i, size * size + 1);
        }

        // initialize all nodes status to blocked (-1)
        isOpenGrids = new boolean[size * size];
        for (int i = 0; i < size * size; i++) {
            isOpenGrids[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int rid = row - 1;
        int cid = col - 1;
        // corner cases
        if (rid < 0 || rid >= size || cid < 0 || cid >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        // return if already opened
        if (isOpen(row, col)) return;

        int index = rid * size + cid;

        // 1. open
        isOpenGrids[index] = true;

        // 2. check for open sites (four times)
        int index1 = (rid - 1) * size + cid;
        int index2 = rid * size + (cid - 1);
        int index3 = rid * size + (cid + 1);
        int index4 = (rid + 1) * size + cid;

        // 2.1
        if (index1 >= 0 && index1 < size * size) {
            if (isOpenGrids[index1]) {
                // union
                weightedQuickUnionUF.union(index, index1);
            }
        }

        // 2.2
        if (index2 >= 0 && index2 < size * size) {
            if (isOpenGrids[index2]) {
                // union
                weightedQuickUnionUF.union(index, index2);
            }
        }

        // 2.3
        if (index3 >= 0 && index3 < size * size) {
            if (isOpenGrids[index3]) {
                // union
                weightedQuickUnionUF.union(index, index3);
            }
        }

        // 2.4
        if (index4 >= 0 && index4 < size * size) {
            if (isOpenGrids[index4]) {
                // union
                weightedQuickUnionUF.union(index, index4);
            }
        }
    }

    // is the site (row, col) open (status == 0)?
    public boolean isOpen(int row, int col) {
        int rid = row - 1;
        int cid = col - 1;
        // corner cases
        if (rid < 0 || rid >= size || cid < 0 || cid >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        int index = rid * size + cid;
        if (isOpenGrids[index]) return true;
        return false;
    }

    // is the site (row, col) full (connected to the Start Site)?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;
        int rid = row - 1;
        int cid = col - 1;
        // corner cases
        if (rid < 0 || rid >= size || cid < 0 || cid >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return weightedQuickUnionUF.connected(rid * size + cid, size * size);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size * size; i++) {
            if (isOpenGrids[i]) count++;
        }
        return count;
    }

    // does the system percolates
    public boolean percolates() {
        return weightedQuickUnionUF.connected(size * size, size * size + 1);
    }
}
