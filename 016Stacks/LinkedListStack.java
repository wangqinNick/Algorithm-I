public class LinkedListStack<Item> {
    private Node first;

    class Node {
        Item item;
        Node next;
    }

    public LinkedListStack() {
        first = null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
