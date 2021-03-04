public class LinkedStackOfStrings {
    private Node first = null;

    private class Node {
        String item;
        Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node(item, oldFirst);
    }

    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }
}
