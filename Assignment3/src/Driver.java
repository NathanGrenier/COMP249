 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: (include question/part number, if applicable)
 // Due: March 24, 2023
 // -----------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
    private static final String inputFileName = "Assignment3/Files/Read/part1_input_file_names.txt";
    private static final String outputFileName = "Assignment3/Files/Read/part1_output_file_names.txt";
    private static final String outputFileDirectory = "Assignment3/Files/Write";

    /**
     * Opens a file to read data from.
     * 
     * @param name
     * @return Scanner linked to input file.
     */
    private static Scanner readFile(String name) {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(name));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + name + " does not exist.");
        }
        return input;
    }

    /**
     * Opens an output file for modification.
     * If the output file dosen't exist, it will create one.
     * 
     * @param name
     * @param append
     * @return PrintWriter object linked to output file.
     */
    private static PrintWriter writeFile(String name, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileOutputStream(outputFileDirectory + name, append));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + name + "couldn't be found or created");
        }
        return output;
    }

    private static String[][] parseBook(String name) {
        
    }

    private static void createOutputFiles(String[] bookFiles, String[][] outputFileInfo) {
        // Open/create every output file and store them in an array. The order of this array is the same as the order of the genres in the outputFileName.txt file.
        PrintWriter[] outputFiles = new PrintWriter[outputFileInfo.length];
        for (int i=0; i < outputFileInfo.length; i++) {
            outputFiles[i] = writeFile(outputFileInfo[i][2], false);
        }

        // Read each book from the bookFiles array individualy and append their entries to the output files
        for (String file:bookFiles) {
            Scanner read = readFile(file);
            if (read == null) {continue;}
            while (read.hasNextLine()) {

            }
        }
    }

    
    
    
    private static void do_part1() {
        // Open file containing the name of the files that contain the books and their information
        Scanner inputFileNameReader = readFile(inputFileName);
        if (inputFileNameReader == null) {
            System.out.println("The file containing the names of the files that contain the recorded books does not exist. Terminating program.");
            System.exit(0);
        }
        
        // Parse the file containing file names and store the names in an array
        int bookFileCount = Integer.parseInt(inputFileNameReader.nextLine());   // The first line will always be a number
        String[] bookFiles = new String[bookFileCount];
        for (int i=0; i < bookFileCount; i++) {
            bookFiles[i] = inputFileNameReader.nextLine();
        }

        // Parse file containing the names of the genres and file output names
        Scanner outputFileNameReader = readFile(outputFileName);
        if (outputFileNameReader == null) {
            System.out.println("The file containing the names of the genres does not exist. Terminating program.");
            System.exit(0);
        }
        // Parse the file containing the genres and store them in an array
        int genreCount = Integer.parseInt(outputFileNameReader.nextLine());
        String[][] outputFileInfo = new String[genreCount][3]; // Format: [Name, Genre, File Name]
        for (int i=0; i < genreCount; i++) {
            outputFileInfo[i] = outputFileNameReader.nextLine().split(",");
        }


    }
    
    
    public static void main(String[] args) {
        do_part1(); // validating syntax, partition book records based on genre.
        
        /* 
        do_part2(); // validating semantics, read the genre files each into arrays of Book objects,
                    // then serialize the arrays of Book objects each into binary files.
        do_part3(); // reading the binary files, deserialize the array objects in each file, and
                    // then provide an interacive program to allow the user to navigate the arrays.
        */
    }
}
