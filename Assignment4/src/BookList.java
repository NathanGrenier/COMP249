import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

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
                    System.out.println("Created bookYear file at: " + Driver.writeFolderPath + yr + ".txt");
                    noMatches = false;
                }
                write.println(node.b);
            }
            node = node.next;
        } while (node != this.head);
        
        if (!noMatches) {
            write.close();
        } else {
            System.out.println("There are no books that were writen in the year: " + yr);
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
        System.out.println("No book with ISBN=" + isbn + " was found.");
        return false;
    }

    public boolean insertBetween(long isbn1, long isbn2, Book b) {
        if (isEmpty()) return false;
        Node node = this.head;
        do {
            if (node.b.ISBN == isbn1 && node.next.b.ISBN == isbn2) {
                node.next = new Node(b, node.next);
                System.out.println("Succesfully inserted between books: " + isbn1 + " and " + isbn2);
                return true;
            }
            node = node.next;
        } while (node != this.head);
        System.out.println("Could not find consecutive books with isbns: " + isbn1 + " and " + isbn2);
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
        Book temp;
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
            temp = firstMatch.b;
            firstMatch.b = secondMatch.b;
            secondMatch.b = temp;
            System.out.println("Sucesfully swaped books: " + isbn1 + " and " + isbn2);
            return true;
        } else {
            System.out.println("Could not find books with isbn: " + isbn1 + " or " + isbn2 + " to swap.");
            return false;
        }
    }

    public void commit() {
        String outputPath = "Update_Books.txt";
        PrintWriter write = openPrintWriter(Driver.writeFolderPath + outputPath, false);
        if (write == null) {
            System.out.println("Could not open file: " + outputPath);
            return;
        }
        Node node = this.head;
        do {
            write.println(node.b);
            node = node.next;
        } while (node != this.head);
        write.close();
        System.out.println("Commited books in bookList to file: " + Driver.writeFolderPath + outputPath);
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
