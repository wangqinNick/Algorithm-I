public class FixedCapacityStackOfStrings {
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N] = item;
        N++;
    }
    
    public String pop() {
        String item = s[N - 1];
        s[N - 1] = null;
        N--;
        return item;
    }
}
