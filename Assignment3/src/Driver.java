 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: (include question/part number, if applicable)
 // Due: March 24, 2023
 //
 // Note: This program relies on the exsiting file structure. If it isn't ordered the exact same way, it will not work.
 // -----------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import Exceptions.MissingFieldException;
import Exceptions.SyntaxException;
import Exceptions.TooFewFieldsException;
import Exceptions.TooManyFieldsException;
import Exceptions.UnknownGenreException;

public class Driver {
    private static final String inputFileName = "Assignment3/Files/Read/part1_input_file_names.txt";
    private static final String outputFileName = "Assignment3/Files/Read/part1_output_file_names.txt";
    private static final String readFileDirectory = "Assignment3/Files/Read/";
    private static final String writeFileDirectory = "Assignment3/Files/Write/";
    private static final String[] orderOfBookFields = {"Title", "Author", "Price", "ISBN", "Genre", "Year"};
    private static final String commaRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    private static OutputFile[] outputFiles;

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
    private static PrintWriter writeFile(String path, boolean append) {
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileOutputStream(path, append));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + path + " couldn't be found or created");
        }
        return output;
    }

    private static void openOutputFileStreams(OutputFile[] list, boolean append) {
        for (OutputFile file:list) {
            if (file.outputStream == null) {
                file.outputStream = writeFile(file.path, append);
            } else {
                System.out.println("OutputFile: " + file.name + " already has an outputStream opened");
            }
        }
    }

    private static void closeOutputFileStreams(OutputFile[] list) {
        for (OutputFile file:list) {
            file.outputStream.close();
        }
    }

    private static OutputFile findOutputFile(OutputFile[] list, String targetGenre) {
        for (OutputFile file:list) {
            if (file.genre.equals(targetGenre)) {
                return file;
            }
        }
        System.out.println("No outputfile with the genre: " + targetGenre + " could be found.");
        return null;
    }

    private static void validateFieldCount(String fileName, String currentLine) throws SyntaxException {
        String[] record = currentLine.split(commaRegex);
        if (record.length > 6) {
            throw new TooManyFieldsException(fileName, currentLine);
        } else if (record.length < 6) {
            throw new TooFewFieldsException(fileName, currentLine);
        }
    }

    private static void validateFieldValues(String fileName, String currentLine) throws SyntaxException{
        // Validate that no field is empty
        String[] record = currentLine.split(commaRegex);
        for (int i=0; i < record.length; i++) {
            if (record[i].equals("")) {
                throw new MissingFieldException(fileName, currentLine, orderOfBookFields[i]);
            }
        }
    }

    private static void validateGenres(String fileName, String currentLine, OutputFile[] list) throws SyntaxException{
        String[] record = currentLine.split(commaRegex);
        // index 4 corresponds to the genre. Format: [Title, Author, Price, ISBN, Genre, year]
        for (OutputFile file:list) {
            if (file.genre.equals(record[4]) && !record[4].equals("BAD_SYN")) {
                return;
            }
        }
        throw new UnknownGenreException(fileName, currentLine);
    }

    private static void createOutputFiles(String[] bookFiles, String[][] outputFileInfo) {
        // Create every outputFile and store them in an array
        outputFiles = new OutputFile[outputFileInfo.length];
        for (int i=0; i < outputFileInfo.length; i++) {
            outputFiles[i] = new OutputFile(outputFileInfo[i][0], outputFileInfo[i][1], outputFileInfo[i][2]);
        }
        // Open the outputStreams of the outputFiles
        openOutputFileStreams(outputFiles, false);

        // Read each book from the bookFiles array individualy and append their entries to the output files
        String currentLine;
        for (String file:bookFiles) {
            Scanner read = readFile(file);
            if (read == null) {continue;}   // If a file dosen't exsist, skip it
            while (read.hasNextLine()) {
                currentLine = read.nextLine();
                // Validate book entry before writing book entry
                try {
                    validateFieldCount(file, currentLine);
                    validateFieldValues(file, currentLine);
                    validateGenres(file, currentLine, outputFiles);
                } catch (SyntaxException e) {
                    e.logError(findOutputFile(outputFiles, "BAD_SYN").outputStream);
                    continue;
                }
                // Store book in correct output file
                OutputFile currentOutputFile = findOutputFile(outputFiles, currentLine.split(commaRegex)[4]);   // Find output file with the genre of the currentLine
                currentOutputFile.outputStream.println(currentLine);
                currentOutputFile.entryCount++; // Increment entryCount of outputFile
            }
            // Close book file after reading all of its contents
            read.close();
        }
    
        // Close all output streams
        closeOutputFileStreams(outputFiles);
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
            bookFiles[i] = readFileDirectory + inputFileNameReader.nextLine();
        }


        // Open file containing the names of the output files
        Scanner outputFileNameReader = readFile(outputFileName);
        if (outputFileNameReader == null) {
            System.out.println("The file containing the names of the genres does not exist. Terminating program.");
            System.exit(0);
        }
        // Parse the file containing the genres and store them in an array (includes the BAD_SYN genre for the error file)
        int genreCount = Integer.parseInt(outputFileNameReader.nextLine());
        String[][] outputFileInfo = new String[genreCount][3]; // Format: [Name, Genre, FileName]
        for (int i=0; i < genreCount; i++) {
            outputFileInfo[i] = outputFileNameReader.nextLine().split(commaRegex);
            for (int j=0; j < outputFileInfo[i].length; j++) {
                outputFileInfo[i][j] = outputFileInfo[i][j].trim();
            }
            outputFileInfo[i][2] = writeFileDirectory + outputFileInfo[i][2];   // Modify the path to the output files
        }
 
        // Create the output files that contain validated book entires
        createOutputFiles(bookFiles, outputFileInfo);

    }

    private static void doPart2() {
        
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
