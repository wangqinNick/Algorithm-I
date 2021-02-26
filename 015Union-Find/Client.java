import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Client {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF quickFindUF = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!quickFindUF.connected(p, q)) {
                quickFindUF.union(p, q);
                StdOut.println(p + " - " + q);
            } else {
                StdOut.println(p + " " + q + " already connected");
            }
        }
    }
}
