/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /** flatten 2D array. */
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    /** false blocked, true open. */
    private boolean[] isOpenGrids;
    /** row and column size */
    private final int size;
    /** count */
    private int openNum;

    /**
     * creates n-by-n grid, with all sites initially blocked.
     *
     * @param n grid-size
     */
    public Percolation(final int n) {

        // validation for the size
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid grids size");
        }
        this.size = n;

        // creates the UF object
        // +2 special nodes (for checking percolation)
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(size * size + 2);
        this.openNum = 0;

        // initialize all nodes status to blocked (-1)
        isOpenGrids = new boolean[size * size];
        for (int i = 0; i < size * size; i++) {
            isOpenGrids[i] = false;
        }
    }

    /**
     * opens the site (row, col) if it is not open already.
     *
     * @param row row
     * @param col col
     */
    public void open(final int row, final int col) {
        int rid = row - 1;
        int cid = col - 1;
        // corner cases
        if (rid < 0 || rid >= size || cid < 0 || cid >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        // return if already opened
        if (isOpen(row, col)) {
            return;
        }

        int index = mappingIndex(rid, cid); // (row - 1) * size + (col - 1)

        // 1. open
        isOpenGrids[index] = true;
        openNum++;

        // 2. union the first row with Start and last row with End if necessary
        if (rid == 0) {
            weightedQuickUnionUF.union(index, size * size);
        }

        /** if (rid == size - 1) {
         //    if (isFull(row, col)) {
         //        weightedQuickUnionUF.union(index, size * size + 1);
         //   }
         // weightedQuickUnionUF.union(index, size * size + 1);
         } **/

        // 3. union consecutive open sites (four times)
        // rid = 7, cid = 9
        int indexUpRid = rid - 1; // 6

        int indexDownRid = rid + 1; // 8

        int indexLeftCid = cid - 1; // 8

        int indexRightCid = cid + 1; // 10

        // Left
        if (indexLeftCid >= 0) {
            int i = mappingIndex(rid, indexLeftCid);
            if (isOpenGrids[i]) {
                weightedQuickUnionUF.union(index, i);
            }
        }

        // Up
        if (indexUpRid >= 0) {
            int i = mappingIndex(indexUpRid, cid);
            if (isOpenGrids[i]) {
                weightedQuickUnionUF.union(index, i);
            }
        }

        // Right
        if (indexRightCid < size) {
            int i = mappingIndex(rid, indexRightCid);
            if (isOpenGrids[i]) {
                weightedQuickUnionUF.union(index, i);
            }
        }

        // Down
        if (indexDownRid < size) {
            int i = mappingIndex(indexDownRid, cid);
            if (isOpenGrids[i]) {
                weightedQuickUnionUF.union(index, i);
            }
        }
    }

    /**
     * is the site (row, col) open (status == 0)?
     *
     * @param row row
     * @param col col
     * @return true if the site is open
     */
    public boolean isOpen(final int row, final int col) {
        int rid = row - 1;
        int cid = col - 1;
        // corner cases
        if (rid < 0 || rid >= size || cid < 0 || cid >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        int index = mappingIndex(rid, cid);
        if (isOpenGrids[index]) {
            return true;
        }
        return false;
    }

    /**
     * is the site (row, col) full (connected to the Start Site)?
     *
     * @param row row
     * @param col col
     * @return true if the site is full
     */
    public boolean isFull(final int row, final int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int rid = row - 1;
        int cid = col - 1;
        // corner cases
        if (rid < 0 || rid >= size || cid < 0 || cid >= size) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return weightedQuickUnionUF.find(mappingIndex(rid, cid))
                == weightedQuickUnionUF.find(size * size);
    }

    /**
     * returns the number of open sites.
     *
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return openNum;
    }

    /**
     * does the system percolates?
     *
     * @return true if the system percolates
     */
    public boolean percolates() {
        //  return weightedQuickUnionUF.find(size * size)
        //        == weightedQuickUnionUF.find(size * size + 1);
        for (int i = 0; i < size; i++) {
            if (weightedQuickUnionUF.find(size * size)
                    == weightedQuickUnionUF.find(size * (size - 1) + i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * map 2D array rows and cols to 1D array index.
     *
     * @param rid row - 1
     * @param cid col - 1
     * @return 1D index
     */
    private int mappingIndex(final int rid, final int cid) {
        return rid * size + cid;
    }
}
