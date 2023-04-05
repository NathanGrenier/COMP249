import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    static final String writeFolderPath = "Assignment4/Files/Write/";
    private static final String readFolderPath = "Assignment4/Files/Read/";
    private static final String bookFileName = "Books.txt";
    private static final String errFileName = "YearErr.txt";
    
    private static void readAndStoreBooksInFile(ArrayList<Book> arrLst, BookList bkLst) {
        Scanner read = null;
        try {
            read = new Scanner(new FileInputStream(readFolderPath + bookFileName));

            String[] entry;
            Book currentBook;
            while (read.hasNextLine()) {
                entry = read.nextLine().split(",");
                currentBook = new Book(
                                    entry[0], 
                                    entry[1], 
                                    Double.parseDouble(entry[2]), 
                                    Long.parseLong(entry[3]), 
                                    entry[4], 
                                    Integer.parseInt(entry[5])
                                );
                // If the Book's year is greater than or equal to 2024, add it to the error ArrayList
                if (Integer.parseInt(entry[5]) >= 2024) {
                    arrLst.add(currentBook);
                } else {
                    // Add the correct entries to the circular linkedlist
                    bkLst.addToEnd(currentBook);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File containing Book entries was not found, terminating.");
            System.exit(0);
        } finally {
            if (read != null) {read.close();}
        }
    }

    private static void writeBooksToErrorFile(ArrayList<Book> arrLst) {
        PrintWriter write = null;
        try {
            if (arrLst.size() > 0) {
                write = new PrintWriter(new FileOutputStream(writeFolderPath + errFileName));
                // Write the Books to the Error File
                for (Book book: arrLst) {
                    write.println(book);
                }
            } else {
                System.out.println("There are no incorrect Book records");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not create Error File, terminating.");
            System.exit(0);
        } finally {
           if (write != null) {write.close();}
        }
    }

    public static void main(String[] args) {
        ArrayList<Book> arrLst = new ArrayList<>();
        BookList bkLst = new BookList();
        Book testBook = new Book("test", "test",-1,-1,"test",-1);
        
        readAndStoreBooksInFile(arrLst, bkLst);

        writeBooksToErrorFile(arrLst);

        bkLst.displayContent();

        bkLst.storeRecordsByYear(1);

        System.out.println(bkLst.insertBefore(1557835659, testBook));
        bkLst.displayContent();
    }
}
