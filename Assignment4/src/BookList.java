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
            Node node = this.head;
            // If the node after the head is null, return the head
            if (node.next == null) {
                return node;
            }
            do {
                node = node.next;
            } while (node.next != this.head && node.next != null && node.next != this.head.next);
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

    private PrintWriter openPrintWriter(String fileName, boolean append) {
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(fileName, append));
            return write;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't create file " + fileName);
            return null;
        }
    }

    public void storeRecordsByYear(int yr) {
        PrintWriter write = null;
        Node node = this.head;
        boolean noMatches = true;
        if (node == null) {
            System.out.println("No nodes exist in the linked list");
            return;
        }
        
        do {
            if (node.b.year == yr) {
                if (noMatches) {
                    write = openPrintWriter(Driver.writeFolderPath + yr + ".txt", false);
                    if (write == null) return;
                    noMatches = false;
                }
                write.println(node.b);
            }
            node = node.next;
        } while (node != this.head);
        
        if (!noMatches) {
            write.close();
        }
    }
 
    public boolean insertBefore(long isbn, Book b) {
        return false;
    }

    public void displayContent() {
        Node node = this.head;
        do {
            System.out.println(node.b + " ==>");
            node = node.next;
        } while(node != this.head);
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
