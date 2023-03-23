 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1, 2, and 3
 // Due: March 24, 2023
 //
 // This is a helper object. It stores useful information about each file that has to be output.
 // -----------------------------------------------------
package Main;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * Contains: 
 * 1. The name, genre, path of an output file (taken from output_file_name.txt).
 * 2. The number of entries/records contained in the file.
 * 3. An array of Book objects (used in part 3)
 * 4. A PrintWriter and ObjectOutputStream to write to the output files.
 * 5. A binary path that is a modified version of the path taken from output_file_name.txt.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class OutputFile {
    public String name;
    public String genre;
    public String path;
    public String binaryPath;
    public int entryCount;
    public PrintWriter outputStream;
    public ObjectOutputStream binaryOutputStream;
    public Book[] books;
    
    public OutputFile(String name, String genre, String path) {
        this.name = name;
        this.genre = genre;
        this.path = path;
        this.entryCount = 0;
        this.outputStream = null;
        this.binaryOutputStream = null;
        this.books = new Book[0];
    }

    /**
     * Instance method that takes assigns the binaryPath to a modified version of the OutputFile's current path by adding a fileExtension and directory.
     * 
     * @param fileExtension
     * @param directory
     */
    public void prepareBinaryOutputFile(String fileExtension, String directory) {
        String[] componenets = this.path.split("/");
        for (int i=0; i < componenets.length - 1; i++) {
            if (i == 0) {
                this.binaryPath = componenets[i];
            } else {
                this.binaryPath = this.binaryPath + "/" + componenets[i];
            }
        }
        this.binaryPath = this.binaryPath + directory + componenets[componenets.length - 1] + fileExtension;
    }

    /**
     * Adds a single Book object to the object's array of Book objects (this.books).
     * 
     * @param newBook
     */
    public void addBook(Book newBook) {
        Book[] newList = new Book[this.books.length + 1];
        for (int i=0; i < this.books.length; i++) {
            newList[i] = this.books[i];
        }
        newList[newList.length - 1] = newBook;

        this.books = newList;
    }
}
