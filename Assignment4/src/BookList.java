import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.PortUnreachableException;

public class BookList {
    Node head;

    public BookList() {
        this.head = null;
    }

    private Node getTail() {
        if (isEmpty()) {
            return null;
        }
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

    private void pointTailToHead() {
        Node tail = this.getTail();
        if (tail != null) {
            tail.next = this.head;
        } else {
            // Only happens if the linkedlist has no nodes
            System.out.println("No tail exists.");
        }
    }

    private boolean isEmpty() {
        if (this.head == null) {
            System.out.println("No nodes exist in the linked list");
            return true;
        } else {
            return false;
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
        
        if (isEmpty()) return;
        
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
        if (isEmpty()) return false;
        Node node = this.head;
        if (node.b.ISBN == isbn) {
            addToStart(b);
            return true;
        }
        do {
            if (node.next.b.ISBN == isbn) {
                node.next = new Node(b, node.next);
                return true;
            }
            node = node.next;
        } while (node != head);
        return false;
    }

    public boolean insertBetween(long isbn1, long isbn2, Book b) {
        if (isEmpty()) return false;
        Node node = this.head;
        do {
            if (node.b.ISBN == isbn1 && node.next.b.ISBN == isbn2) {
                node.next = new Node(b, node.next);
                return true;
            }
            node = node.next;
        } while (node != this.head);
        return false;
    }

    public void displayContent() {
        if (isEmpty()) return;
        Node node = this.head;
        do {
            System.out.println(node.b + " ==>");
            node = node.next;
        } while(node != this.head);
        System.out.println("===> head");
    }

    public boolean delConsecutiveRepeatedRecords() {
        if (isEmpty()) return false;
        Node node = this.head;
        while (node.b.ISBN == node.next.b.ISBN) {
            node.next = node.next.next;
        }
        do {
            if (node.b.ISBN == node.next.b.ISBN) {
                System.out.println("Removed: " + node.next.b);
                node.next = node.next.next;
                continue;
            }
            node = node.next;
        } while (node.next != this.head);
        if (node.b.ISBN == this.head.b.ISBN) {
            System.out.println("Removed: " + node.next.b);
            node.next = node.next.next;
            this.head = this.head.next;
            pointTailToHead();
        }
        return true;
    }

    public boolean delConsecutiveRepeatedRecordsAlternative() {
        if (isEmpty()) return false;
        Node node = this.head;
        do {
            if (node.b.ISBN == node.next.b.ISBN) {
                System.out.println("Removed: " + node.next.b);
                if (node.next == this.head) {
                    this.head = this.head.next;
                    pointTailToHead();
                    continue;
                }
                node.next = node.next.next;
                continue;
            }
            node = node.next;
        } while (node != this.head || (this.head.b.ISBN == this.head.next.b.ISBN));
        return true;
    }

    public BookList extractAuthList(String aut) {
        BookList newList = null;
        Node node = this.head;
        do {
            if (node.b.author.equals(aut)) {
                if (newList == null) {
                    newList = new BookList();
                }
                newList.addToEnd(node.b);
            }
            node = node.next;
        } while(node != this.head);
        if (newList == null) {
            System.out.println("No books with the author: " + aut + " was found. Returned null");
        }
        return newList;
    }

    public boolean swap(long isbn1, long isbn2) {
        Node node = this.head;
        Node firstMatch = null;
        Node secondMatch = null;
        do {
            if (node.b.ISBN == isbn1 && firstMatch == null) {
                firstMatch = node;
            }
            if (node.b.ISBN == isbn2 && secondMatch == null) {
                secondMatch = node;
            }
            node = node.next;
        } while (node != this.head && (firstMatch == null || secondMatch == null));
        if (firstMatch != null && secondMatch != null) {
        }
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
