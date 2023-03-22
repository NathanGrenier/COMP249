package Main;
 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1,2 and 3
 // Due: March 24, 2023
 //
 // Note: This program relies on the exsiting file structure. If it isn't ordered the exact same way, it will not work. The following is what the structure should look like:
 // Assignment3/
 // ├─ Files/
 // │  ├─ Read/
 // │  ├─ Write/
 // ├─ src/
 // -----------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

import Exceptions.BadIsbn10Exception;
import Exceptions.BadIsbn13Exception;
import Exceptions.BadPriceException;
import Exceptions.BadYearException;
import Exceptions.MissingFieldException;
import Exceptions.SemanticException;
import Exceptions.SyntaxException;
import Exceptions.TooFewFieldsException;
import Exceptions.TooManyFieldsException;
import Exceptions.UnknownGenreException;

public class Driver {
    private static final String inputFilesPath = "Assignment3/Files/Read/part1_input_file_names.txt";
    private static final String outputFilesPath = "Assignment3/Files/Read/part1_output_file_names.txt";
    private static final String readFileDirectory = "Assignment3/Files/Read/";
    private static final String writeFileDirectory = "Assignment3/Files/Write/";
    private static final String[] orderOfBookFields = {"Title", "Author", "Price", "ISBN", "Genre", "Year"};
    private static final String commaRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

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
            if (file.outputStream != null) {
                file.outputStream.close();
            } else {
                System.out.println("OutputFile: " + file.name + " is already closed");
            }
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

    private static OutputFile[] createOutputFiles(String[][] outputFileInfo) {
        // Create every outputFile and store them in an array
        OutputFile[] outputFiles = new OutputFile[outputFileInfo.length];
        for (int i=0; i < outputFileInfo.length; i++) {
            outputFiles[i] = new OutputFile(outputFileInfo[i][0], outputFileInfo[i][1], outputFileInfo[i][2]);
        }
        return outputFiles;
    }

    
    private static void do_part1() {
        // Open file containing the name of the files that contain the books and their information
        Scanner inputFileNameReader = readFile(inputFilesPath);
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
        Scanner outputFileNameReader = readFile(outputFilesPath);
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
        OutputFile[] outputFiles = createOutputFiles(outputFileInfo);

        // Open the outputStreams of the outputFiles
        openOutputFileStreams(outputFiles, false);

        // Read each book from the bookFiles array individualy and append their entries to the output files
        String currentLine;
        for (String file:bookFiles) {
            Scanner read = readFile(file);
            if (read == null) {continue;}   // If a file dosen't exsist, skip it
            while (read.hasNextLine()) {
                currentLine = read.nextLine();
                // Validate book entry syntactically before writing book entry
                try {
                    validateFieldCount(file, currentLine);
                    validateFieldValues(file, currentLine);
                    validateGenres(file, currentLine, outputFiles);
                } catch (SyntaxException e) {
                    e.logError(findOutputFile(outputFiles, "BAD_SYN"));
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


    private static void validateIsbn10(String fileName, String currentLine) throws SemanticException {
        int sum = 0;
        String [] record = currentLine.split(commaRegex);
        String[] digits = record[3].split("");    // record[3] corresponds to the ISBN field
        // If the ISBN is 13 digits long, return from function as we don't want to validate it here
        if (digits.length == 13) {
            return;
        }
        
        // Validate that the isbn is 10 digits.
        if (digits.length != 10) {
            throw new BadIsbn10Exception(fileName, currentLine);
        }
        
        // Validate that the isbn only contains numbers
        try {
            for (int i=0; i < digits.length; i++) {
                sum += Integer.parseInt(digits[i]) * (10 - i); 
            }
        } catch (NumberFormatException e) {
            throw new BadIsbn10Exception(fileName, currentLine);
        }
        
        // Validate that sum(10x1 + 9x2 + 8x3 + 7x4 + 6x5 + 5x6 + 4x7 + 3x8 + 2x9 + 1x10) is a multiple of 11
        if (sum % 11 != 0) {
            throw new BadIsbn10Exception(fileName, currentLine);
        }  
    }

    private static void validateIsbn13(String fileName, String currentLine) throws SemanticException {
        int sum = 0;
        String [] record = currentLine.split(commaRegex);
        String[] digits = record[3].split("");    // record[3] corresponds to the ISBN field
        // If the ISBN is 10 digits long, return from function as we don't want to validate it here
        if (digits.length == 10) {
            return;
        }
        
        // Validate that the isbn is 13 digits
        if (digits.length != 13) {
            throw new BadIsbn13Exception(fileName, currentLine);
        }
        
        // Validate that the isbn only contains numbers
        try {
            for (int i=0; i < digits.length; i++) {
                sum += (i + 1) % 2 == 0 ? Integer.parseInt(digits[i]) * 3 : Integer.parseInt(digits[i]); 
            }
        } catch (NumberFormatException e) {
            throw new BadIsbn13Exception(fileName, currentLine);
        }
        
        // Validate that sum(x1 +3x2 +x3 +3x4 +x5 +3x6 +x7 +3x8 +x9 +3x10 +x11 +3x12 +x13) is a multiple of 10
        if (sum % 10 != 0) {
            throw new BadIsbn13Exception(fileName, currentLine);
        }  
    }

    private static void validatePrice(String fileName, String currentLine) throws SemanticException {
        String [] record = currentLine.split(commaRegex);
        
        if (Double.parseDouble(record[2]) < 0) {
            throw new BadPriceException(fileName, currentLine);
        }
    }

    private static void validateYear(String fileName, String currentLine) throws SemanticException {
        String [] record = currentLine.split(commaRegex);
        int year = Integer.parseInt(record[5]);
        
        if (year < 1995 || year > 2010) {
            throw new BadYearException(fileName, currentLine);
        }
    }
    
    private static Book[] addBook(Book[] list, Book book) {
        Book[] newList = new Book[list.length + 1];
        for (int i=0; i < list.length; i++) {
            newList[i] = list[i];
        }
        newList[newList.length - 1] = book;

        return newList;
    }
    
    private static void do_part2() {
        // Open file containing the names of the output files
        Scanner outputFileNameReader = readFile(outputFilesPath);
        if (outputFileNameReader == null) {
            System.out.println("The file containing the names of the genres does not exist. Terminating program.");
            System.exit(0);
        }
        // Parse the file containing the outputFiles and store them in an array (excludes the BAD_SYN genre file)
        int genreCount = Integer.parseInt(outputFileNameReader.nextLine()) - 1; // the -1 removes the syntax error file from the list
        String[][] outputFileInfo = new String[genreCount][3]; // Format: [Name, Genre, FileName]
        for (int i=0; i < genreCount; i++) {
            outputFileInfo[i] = outputFileNameReader.nextLine().split(commaRegex);
            for (int j=0; j < outputFileInfo[i].length; j++) {
                outputFileInfo[i][j] = outputFileInfo[i][j].trim();
            }
            outputFileInfo[i][2] = writeFileDirectory + outputFileInfo[i][2];   // Modify the path to the output files
        }

        // Create the outputFiles
        OutputFile[] outputFiles = createOutputFiles(outputFileInfo);

        // Add the semantic error text file to the array of output Objects
        outputFiles = Arrays.copyOf(outputFiles, outputFiles.length + 1);
        outputFiles[outputFiles.length - 1] = new OutputFile("Bad CSV-formatted Output Records", "BAD_SEM", writeFileDirectory + "semantic_error_file.txt");
        
        // Open the ObjectOutputStreams of the OutputFile objects
        for (OutputFile file:outputFiles) {
            if (file.genre.equals("BAD_SEM")) {continue;}
            file.prepareBinaryOutputFile(".ser","/Binary/");
            try {
                file.binaryOutputStream = new ObjectOutputStream(new FileOutputStream(file.binaryPath));
            } catch (IOException e) {
                System.out.println("Output file: " + file.binaryPath + " had a problem with its file output");
            }
        }

        // Create outputStream for the semantic error file
        outputFiles[outputFiles.length - 1].outputStream = writeFile(outputFiles[outputFiles.length - 1].path, false);

        // Read each output file one by one and create Book objects after validating the semantics of their entries. If there is a semantic error, log the error to semantic_error_file.txt
        String currentLine;
        for (OutputFile file:outputFiles) {
            if (file.genre.equals("BAD_SEM")) {continue;}   // Skip the error file because it dosen't contain any books
            Scanner read = readFile(file.path);
            while (read.hasNextLine()) {
                currentLine = read.nextLine();
                // Validate book entry semantically before writing book entry
                try {
                    validateIsbn10(file.path, currentLine);
                    validateIsbn13(file.path, currentLine);
                    validatePrice(file.path, currentLine);
                    validateYear(file.path, currentLine);
                } catch (SemanticException e) {
                    e.logError(findOutputFile(outputFiles, "BAD_SEM"));
                    continue;
                }
                // Create Book object and store it in the Book array of the OutputFile objects
                String[] entry = currentLine.split(commaRegex);
                file.addBook(new Book(entry[0], entry[1], Double.parseDouble(entry[2]), entry[3], Integer.parseInt(entry[5])));
            }
            // Serialize the array of Books to their respective binary files
            try {
                file.binaryOutputStream.writeObject(file.books);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close ObjectOutputStream for current file
                try {
                    file.binaryOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Close outputStream of semantic error file
        outputFiles[outputFiles.length - 1].outputStream.close();
    }
    
    
    public static void main(String[] args) {
        do_part1(); // validating syntax, partition book records based on genre.
        
        do_part2(); // validating semantics, read the genre files each into arrays of Book objects,
                    // then serialize the arrays of Book objects each into binary files.
        /*
        do_part3(); // reading the binary files, deserialize the array objects in each file, and
                    // then provide an interacive program to allow the user to navigate the arrays.
        */
    }
}
