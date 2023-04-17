public class LinkedList {
    Node head;
    int size;
    
    public void insert(Store s) {
        if (this.head == null) {
            this.head = new Node(s, null);
            return;
        }

        if (s.sale > this.head.store.sale) {
            // Insert before head
            this.head = new Node(s, this.head);
            return;
        }
        Node node = this.head;
        while (node.next != null && s.sale < node.next.store.sale) {
            node = node.next;
        }
        node.next = new Node(s, node.next);

    }

    public void display() {
        Node node = this.head;
        while (node != null) {
            System.out.println(node.store);
            node = node.next;
        }
    }
    
    public void remove(Store s) {
        if (this.head == null) {
            System.out.println("No nodes in list");
            return;
        }
        if (this.head.store.sale == s.sale) {
            this.head = this.head.next;
        }
        
        Node node = this.head;
        while (node.next != null) {
            if (node.next.store.sale == s.sale) {
                node.next = node.next.next;
            }
            node = node.next;
        }
    }
    
    private class Node {
        private Store store;
        private Node next;

        public Node() {
            this.store = null;
            this.next = null;
        }

        public Node(Store store, Node next) {
            this.store = store;
            this.next = next;
        }
    }
}
