/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int first;
    private int last;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = 0;
        last = 0;
        n = 0;
        q = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // add the item (to the back)
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        q[last] = item;
        n++;
        if (n == q.length) {
            resize(q.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(n);
        Item item = q[index];
        n--;
        for (int i = index; i < n; i++) {
            int j = (first + i) % q.length;
            q[j] = q[j + 1];
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(n);
        Item item = q[index];
        return item;
    }

    // return an iterator over items in order in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final int[] order = StdRandom.permutation(n);
        private int current = 0;

        public boolean hasNext() {
            return current != n;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = q[order[current]];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last = n;
    }

    public static void main(String[] args) {
        /** for unit testing */
    }
}
