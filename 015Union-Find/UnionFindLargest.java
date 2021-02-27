public class UnionFindLargest {
    private int[] id;
    private int[] sz;  // number of nodes of root i
    private int[] max;

    public UnionFindLargest(int N) {
        id = new int[N];
        sz = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;  // default 1
            max[i] = i;
        }
    }

    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int p_root = root(p);
        int q_root = root(q);
        if (p_root == q_root) return;
        // if p's root is not q's root, needs union operation
        // decide which tree comes on the top
        if (sz[p_root] < sz[q_root]) {
            // p's root's tree under q's root's tree
            id[p_root] = q_root;
            sz[q_root] += sz[p_root];  // increase the larger tree's root's weight
        } else {
            id[q_root] = p_root;
            sz[p_root] += sz[q_root];
        }
        if (max[p_root] < max[q_root]) {
            // p's root's tree under q's root's tree
            max[p_root] = max[q_root];
        } else {
            max[q_root] = max[p_root];
        }
    }

    public int find(int i) {
        return max[i];
    }
}
