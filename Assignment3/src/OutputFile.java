import java.io.PrintWriter;

public class OutputFile {
    String name;
    String genre;
    String path;
    int entryCount;
    PrintWriter outputStream;
    
    public OutputFile(String name, String genre, String path) {
        this.name = name;
        this.genre = genre;
        this.path = path;
        this.entryCount = 0;
        this.outputStream = null;
    }
}
