import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BookList {
    Node head;

    public BookList() {
        this.head = null;
    }

    public void addToStart(Book b) {
        this.head = new Node(b, this.head);
    }

    public void storeRecordsByYear(int yr) {
        PrintWriter write = null;
        try {
            write = new PrintWriter(new FileOutputStream(Driver.writeFolderPath + yr + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't create file " + yr + ".txt" );
        }
    }

    public void displayContent() {
        Node node = this.head;
        while (node != null) {
            System.out.println(node.b + " ==>");
            node = node.next;
        }
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
