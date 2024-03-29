 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #4
 // Due: April 17, 2023
 // -----------------------------------------------------
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

 /**
 * Runs the code that:
 * 1) Reads the content of Books.txt and stores the incorrect entries in an arrayList. The correct entries are added to the linkedList of books.
 * 2) Writes the incorrect entries in the arrayList to an error file named YearErr.txt.
 * 3) Displays a menu to the user that allows them to perform certain actions. The actions are as follows:
 *   - Store all books of a certain year into a new file
 *   - Delete all consecutive records in the linkedList of books
 *   - Create a new linked list that contains the records of a specific author
 *   - Insert a new node before a node in the existing linked list with a specific ISBN
 *   - Inser a new node inbetween 2 existing consecutive nodes corresponding to the passed ISBN values.
 *   - Swap the data of 2 nodes in the linked list that correspond to the passed ISBN values
 *   - Commit the records in the linked list to a file name Update_Books.txt
 *   - Exit the program (does not auto commit)
 * 
 * @author Nathan Grenier
 * @version 1.0 
 */
public class Driver {
    static final String writeFolderPath = "Assignment4/Files/Write/";
    private static final String readFolderPath = "Assignment4/Files/Read/";
    private static final String bookFileName = "Books.txt";
    private static final String errFileName = "YearErr.txt";
    
    /**
     * Reads the book entries in the specified file and stores the correct entries in a linkedList of Books. The incorrect entries are storred in an arrayList.
     * 
     * @param arrLst
     * @param bkLst
     */
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

    /**
     * Creates a new error file named YearErr.txt if there are elements in the arrayList of incorrect book entries. Writes all of these elements to the file.
     * 
     * @param arrLst
     */
    private static void writeBooksToErrorFile(ArrayList<Book> arrLst) {
        PrintWriter write = null;
        try {
            if (arrLst.size() > 0) {
                write = new PrintWriter(new FileOutputStream(writeFolderPath + errFileName));
                System.out.println("YearError File Created");
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

    /**
     * Displays the command menu to the user through the console.
     */
    private static void displayMenu() {
        System.out.println("\nTell me what you want to do:");
        System.out.println("\t1) Give me a year # and I would extract all records of that year and store them in a file for that year;\n"
                         + "\t2) Ask me to delete all consecutive repeated records;\n"
                         + "\t3) Give me an author name and I will create a new list with the records of this author and display them;\n"
                         + "\t4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n"
                         + "\t5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!;\n"
                         + "\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!;\n"
                         + "\t7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n"
                         + "\t8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!;");
    }

    /**
     * Prompts the user to enter an integer. Will re-prompt the user if they enter another data type.
     * 
     * @return integer value
     */
    private static int getIntegerInput() {
        Scanner in = new Scanner(System.in);
        int input;
        if (in.hasNextInt()) {
            input = in.nextInt();
        } else {
            System.out.print("You must enter a number. Please try again: ");
            input = getIntegerInput();
        }
        return input;
    }

    /**
     * @return String inputed through console
     */
    private static String getStringInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Prompts the user to enter the attributes in order to create a new book object.
     * 
     * @return Book object with the user specified values.
     */
    private static Book createBook() {
        System.out.println("Enter the fields to create a new book object:");
        System.out.print("Title: ");
        String title = getStringInput();
        System.out.print("Author: ");
        String author = getStringInput();
        System.out.print("Price: ");
        int price = getIntegerInput();
        System.out.print("ISBN: ");
        long isbn = getIntegerInput();
        System.out.print("Genre: ");
        String genre = getStringInput();
        System.out.print("Year of Creation: ");
        int year = getIntegerInput();
        return new Book(title, author, price, isbn, genre, year);
    }

    public static void main(String[] args) {
        ArrayList<Book> arrLst = new ArrayList<>();
        BookList bkLst = new BookList();
        
        readAndStoreBooksInFile(arrLst, bkLst);

        writeBooksToErrorFile(arrLst);

        Book newBook;
        long isbn1;
        long isbn2;
        int input = 0;
        do {
            System.out.println("\nHere are the contents of the list\n==================================");
            bkLst.displayContent();
            displayMenu();
            System.out.print("Please enter a number from 1 to 8 corresponding to the action you want: ");
            input = getIntegerInput();
            switch (input){
                case 1:
                    System.out.print("Enter a year: ");
                    int inputYear = getIntegerInput();
                    bkLst.storeRecordsByYear(inputYear);
                    break;
                
                case 2:
                    bkLst.delConsecutiveRepeatedRecords();
                    break;
                                
                case 3:
                    System.out.print("Enter the name of the author you want an extracted list of: ");
                    String author = getStringInput();
                    BookList authorList = bkLst.extractAuthList(author);
                    if (authorList != null) {
                        System.out.println("\nHere are the contents of the author list for: " + author + "\n=============================================");
                        authorList.displayContent();
                    }
                    break;

                case 4:
                    newBook = createBook();
                    System.out.print("Enter the isbn of the book you want to insert before: ");
                    long isbn = getIntegerInput();
                    bkLst.insertBefore(isbn, newBook);
                    break;

                case 5:
                    newBook = createBook();
                    System.out.print("Enter the ISBN of the first book: ");
                    isbn1 = getIntegerInput();
                    System.out.print("Enter the ISBN of the second book: ");
                    isbn2 = getIntegerInput();
                    bkLst.insertBetween(isbn1, isbn2, newBook);
                    break;
                    
                case 6:
                    System.out.print("Enter the ISBN of the first book: ");
                    isbn1 = getIntegerInput();
                    System.out.print("Enter the ISBN of the second book: ");
                    isbn2 = getIntegerInput();
                    bkLst.swap(isbn1, isbn2);
                    break;
                    
                case 7:
                    bkLst.commit();
                    break;

                case 8:
                    System.out.println("Terminating Program.");
                    break;
                
                default:
                    System.out.println("Not a valid command. Please try again.");
                    break;
            }
        } while (input != 8);
    }
}
