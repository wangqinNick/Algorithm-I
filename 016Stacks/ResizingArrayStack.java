public class ResizingArrayStack<Item> {
    private Item[] s;
    private int N = 0;  // points to the index for furture new elements

    public ResizingArrayStack() {
        s = (Item[]) new Object[1];
    }

    public void push(Item item) {
        s[N++] = item;
        if (s.length == N) {
            resize(s.length * 2);
        }
    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && s.length / 4 == N) {
            resize(s.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
}
