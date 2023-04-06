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

    private static int getInput() {
        Scanner in = new Scanner(System.in);
        int input;
        System.out.print("Please enter a number from 1 to 8 corresponding to the action you want: ");
        if (in.hasNextInt()) {
            input = in.nextInt();
        } else {
            System.out.println("You must enter and integer. Please try again.");
            input = getInput();
        }
        return input;
    }

    public static void main(String[] args) {
        ArrayList<Book> arrLst = new ArrayList<>();
        BookList bkLst = new BookList();
        
        readAndStoreBooksInFile(arrLst, bkLst);

        writeBooksToErrorFile(arrLst);

        bkLst.displayContent();

        int input = 0;
        do {
            displayMenu();
            input = getInput();
            switch (input){
                case 1:
                    
                    break;
                
                case 2:
                    break;
                                
                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;
                    
                case 6:
                    break;
                    
                case 7:
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
