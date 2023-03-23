package Main;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

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

    public void addBook(Book newBook) {
        Book[] newList = new Book[this.books.length + 1];
        for (int i=0; i < this.books.length; i++) {
            newList[i] = this.books[i];
        }
        newList[newList.length - 1] = newBook;

        this.books = newList;
    }
}
