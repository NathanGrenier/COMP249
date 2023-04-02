import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BookList {
    Node head;

    public BookList() {
        this.head = null;
    }

    private Node getTail() {
        if (this.head == null) {
            System.out.println("Linked list has no tail.");
            return null;
        } else {
            Node node = this.head.next; // If node == this.head then, node.next would always equal this.head.next. Therfore, skip the head
            // If the node after the head is null, return the head as it is also the tail
            if (node == null) {
                return this.head;
            }
            // this.head.next refers to the last head (before the potential re-assignment of head by addToHead())
            while (node.next != this.head && node.next != null && node.next != this.head.next) {
                node = node.next;
            }
            return node;
        }
    }

    private void pointTailToHead() {
        Node tail = this.getTail();
        if (tail != null) {
            tail.next = this.head;
        } else {
            // Only happens if the linkedlist has no nodes
            System.out.println("No tail exists.");
        }
    }

    public void addToStart(Book b) {
        this.head = new Node(b, this.head);
        this.pointTailToHead();
    }

    public void addToEnd(Book b) {
        Node tail = this.getTail();
        // tail dosen't exist therefore head dosen't exist. So add to start
        if (tail == null) {
            System.out.println("Adding to start as tail dosen't exist.");
            this.addToStart(b);
        } else {
            tail.next = new Node(b, this.head);
        }
    }

    public void storeRecordsByYear(int yr) {
        PrintWriter write = null;
        Node node = this.head;
        boolean noMatchFound = true;
        try {
            if (node != null) {
                while (node.next != this.head) {
                    if (node.b.year == yr) {
                        if (noMatchFound) {
                            write = new PrintWriter(new FileOutputStream(Driver.writeFolderPath + yr + ".txt"));
                            noMatchFound = false;
                        }
                        write.println(node.b);
                    }
                    node = node.next;
                }
                // Evaluate the tail
                if (noMatchFound && node.b.year == yr) {
                    write = new PrintWriter(new FileOutputStream(Driver.writeFolderPath + yr + ".txt"));
                    write.println(node.b);
                } else if (noMatchFound == false) {
                    write.println(node.b);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't create file " + yr + ".txt" );
        } finally {
            if (write != null) {write.close();}
        }
    }

    public void displayContent() {
        Node node = this.head;
        while (node.next != this.head) {
            System.out.println(node.b + " ==>");
            node = node.next;
        }
        System.out.println(node.b + " ==>");
        System.out.println("===> head");
    }

    private class Node {
        private Book b;
        private Node next;

        public Node() {
            this.b = null;
            this.next = null;
        }

        public Node(Book b, Node next) {
            this.b = b;
            this.next = next;
        }
    }
}
