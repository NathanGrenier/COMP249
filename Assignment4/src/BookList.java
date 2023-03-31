import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BookList {
    Node head;

    public BookList() {
        this.head = null;
    }

    public void addToStart(Book b) {
        if (this.head == null) {
            this.head = new Node(b, this.head);
            // Make the last link point to to the head (This only updates the circulatiry of the list when the head node is null. To ensure circulatity, the last node must be updated everytime the head is.)
            this.head.next = this.head;
        } else {
            this.head = new Node(b, this.head);
            // Make list circular
            Node node = this.head.next;
            // Traverse list until last node (that points to the old head)
            while (node.next != this.head.next) {
                node = node.next;
            }
            // Update the head
            node.next = this.head;
        }
    }

    public void storeRecordsByYear(int yr) {
        PrintWriter write = null;
        Node node = this.head;
        try {
            if (node != null) {
                write = new PrintWriter(new FileOutputStream(Driver.writeFolderPath + yr + ".txt"));
                while (node.next != this.head) {

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
