public class LinkedListQueue<Item> {
    Node first;
    Node last;

    class Node {
        Item item;
        Node next;
    }

    public LinkedListQueue() {
        first = null;
        last = null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
