/**
 * The quick find algorithm
 */
public class QuickFindUF {
    // indices of the array represents the member object
    // entries of the array represents the groups of the members
    private int[] id;

    /**
     * Constructs the default array
     *
     * @param N number of members in the array
     */
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * Checks if p member and q member are connected
     *
     * @param p member p
     * @param q member q
     * @return true if p member and q member are connected
     */
    public boolean connected(int p, int q) {
        return id[p] == id[q]; //if p's group equals to q's group
    }

    /**
     * Unions (Connects) two member p and q
     *
     * @param p member p to be connected
     * @param q member q to be connected
     */
    public void union(int p, int q) {
        // firstly find the p's and q's groups
        int pid = id[p];
        int qid = id[q];

        // traverse through the array to change all p's group members' group to q's group
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
    }
}
