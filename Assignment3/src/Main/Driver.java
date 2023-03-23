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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

/**
 * Runs the Book Managment System. This:
 * 1. Validates all the book entires
 * 2. Creates the validated output files
 * 3. Displays a navigation menu to the user that allows them to view any book record in the system.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Driver {
    private static final String inputFilesPath = "Assignment3/Files/Read/part1_input_file_names.txt";
    private static final String outputFilesPath = "Assignment3/Files/Read/part1_output_file_names.txt";
    private static final String readFileDirectory = "Assignment3/Files/Read/";
    private static final String writeFileDirectory = "Assignment3/Files/Write/";
    private static final String[] orderOfBookFields = {"Title", "Author", "Price", "ISBN", "Genre", "Year"};
    private static final String commaRegex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    /* 
  _____           _     __       _    _      _                   ______                _   _                 
 |  __ \         | |   /_ |  _  | |  | |    | |                 |  ____|              | | (_)                
 | |__) |_ _ _ __| |_   | | (_) | |__| | ___| |_ __   ___ _ __  | |__ _   _ _ __   ___| |_ _  ___  _ __  ___ 
 |  ___/ _` | '__| __|  | |     |  __  |/ _ \ | '_ \ / _ \ '__| |  __| | | | '_ \ / __| __| |/ _ \| '_ \/ __|
 | |  | (_| | |  | |_   | |  _  | |  | |  __/ | |_) |  __/ |    | |  | |_| | | | | (__| |_| | (_) | | | \__ \
 |_|   \__,_|_|   \__|  |_| (_) |_|  |_|\___|_| .__/ \___|_|    |_|   \__,_|_| |_|\___|\__|_|\___/|_| |_|___/
                                              | |                                                            
                                              |_|                                                            
*/

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

    /**
     * Takes in a list of OutputFiles and opens their PrintWriters. The append parameter determines if the file will be overwriten or appended.
     * 
     * @param list
     * @param append
     */
    private static void openOutputFileStreams(OutputFile[] list, boolean append) {
        for (OutputFile file:list) {
            if (file.outputStream == null) {
                file.outputStream = writeFile(file.path, append);
            } else {
                System.out.println("OutputFile: " + file.name + " already has an outputStream opened");
            }
        }
    }

    /**
     * Takes a list of OutputFiles and closes their PrintWriters
     * 
     * @param list
     */
    private static void closeOutputFileStreams(OutputFile[] list) {
        for (OutputFile file:list) {
            if (file.outputStream != null) {
                file.outputStream.close();
            } else {
                System.out.println("OutputFile: " + file.name + " is already closed");
            }
        }
    }

    /**
     * Finds the OutputFile from the passed list that has the targetGenre. If no file exists, retun null. 
     * 
     * @param list
     * @param targetGenre
     * @return OutputFile
     */
    private static OutputFile findOutputFile(OutputFile[] list, String targetGenre) {
        for (OutputFile file:list) {
            if (file.genre.equals(targetGenre)) {
                return file;
            }
        }
        System.out.println("No outputfile with the genre: " + targetGenre + " could be found.");
        return null;
    }

    /**
     * Validates the number of fields of a book record. Checks if the book either:
     * 1. Has too many fields
     * 2. Has too few fields
     * 
     * @param fileName
     * @param currentLine
     * @throws SyntaxException
     */
    private static void validateFieldCount(String fileName, String currentLine) throws SyntaxException {
        String[] record = currentLine.split(commaRegex);
        if (record.length > 6) {
            throw new TooManyFieldsException(fileName, currentLine);
        } else if (record.length < 6) {
            throw new TooFewFieldsException(fileName, currentLine);
        }
    }

    /**
     * Throws a SyntaxException if one of the book's fields is empty
     * 
     * @param fileName
     * @param currentLine
     * @throws SyntaxException
     */
    private static void validateFieldValues(String fileName, String currentLine) throws SyntaxException{
        // Validate that no field is empty
        String[] record = currentLine.split(commaRegex);
        for (int i=0; i < record.length; i++) {
            if (record[i].equals("")) {
                throw new MissingFieldException(fileName, currentLine, orderOfBookFields[i]);
            }
        }
    }

    /**
     * Compares the current book record's genre to that of all OutputFiles. If one of them matches, the record is valid.
     * 
     * @param fileName
     * @param currentLine
     * @param list
     * @throws SyntaxException
     */
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

    /**
     * Creates the OutputFile objects using information from the output_file_names file.
     * 
     * @param outputFileInfo
     * @return OutputFile[]
     */
    private static OutputFile[] createOutputFiles(String[][] outputFileInfo) {
        // Create every outputFile and store them in an array
        OutputFile[] outputFiles = new OutputFile[outputFileInfo.length];
        for (int i=0; i < outputFileInfo.length; i++) {
            outputFiles[i] = new OutputFile(outputFileInfo[i][0], outputFileInfo[i][1], outputFileInfo[i][2]);
        }
        return outputFiles;
    }

    /**
     * Reads the file containing the files to be output and parses their information.
     * 
     * @param reader
     * @param genreCount
     * @return String[][] containing each OutputFile's information in the format: [[Name, Genre, path], ...]
     */
    private static String[][] getOutputFilesInfo(Scanner reader, int genreCount) {
        String[][] outputFileInfo = new String[genreCount][3]; // Format: [Name, Genre, FileName]
        for (int i=0; i < genreCount; i++) {
            outputFileInfo[i] = reader.nextLine().split(commaRegex);
            for (int j=0; j < outputFileInfo[i].length; j++) {
                outputFileInfo[i][j] = outputFileInfo[i][j].trim();
            }
            outputFileInfo[i][2] = writeFileDirectory + outputFileInfo[i][2];   // Modify the path to the output files
        }
        return outputFileInfo;
    }
    
    /*  
    _____           _     __ 
    |  __ \         | |   /_ |
    | |__) |_ _ _ __| |_   | |
    |  ___/ _` | '__| __|  | |
    | |  | (_| | |  | |_   | |
    |_|   \__,_|_|   \__|  |_|
    */                       
              
    /**
     * Validates syntax and partition book records based on genre into text files.
     * 
     */
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
        
        String[][] outputFileInfo = getOutputFilesInfo(outputFileNameReader, genreCount); // Format: [Name, Genre, FileName]
 
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

/* 
    _____           _     ___        _    _      _                   ______                _   _                 
    |  __ \         | |   |__ \   _  | |  | |    | |                 |  ____|              | | (_)                
    | |__) |_ _ _ __| |_     ) | (_) | |__| | ___| |_ __   ___ _ __  | |__ _   _ _ __   ___| |_ _  ___  _ __  ___ 
    |  ___/ _` | '__| __|   / /      |  __  |/ _ \ | '_ \ / _ \ '__| |  __| | | | '_ \ / __| __| |/ _ \| '_ \/ __|
    | |  | (_| | |  | |_   / /_   _  | |  | |  __/ | |_) |  __/ |    | |  | |_| | | | | (__| |_| | (_) | | | \__ \
    |_|   \__,_|_|   \__| |____| (_) |_|  |_|\___|_| .__/ \___|_|    |_|   \__,_|_| |_|\___|\__|_|\___/|_| |_|___/
                                                   | |                                                            
                                                   |_|                                                            
*/

    /**
     * Validates the ISBN of the current book record only if it has exactly 10 digits. 
     * A valid ISBN only contains numbers and where sum(10x1 + 9x2 + 8x3 + 7x4 + 6x5 + 5x6 + 4x7 + 3x8 + 2x9 + 1x10) is a multiple of 11.
     * 
     * @param fileName
     * @param currentLine
     * @throws SemanticException
     */
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

    /**
     * Validates the current record's ISBN only if it has exactly 13 digits.
     * A valid ISBN only contains numbers and where sum(x1 +3x2 +x3 +3x4 +x5 +3x6 +x7 +3x8 +x9 +3x10 +x11 +3x12 +x13) is a multiple of 10.
     * 
     * @param fileName
     * @param currentLine
     * @throws SemanticException
     */
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

    /**
     * Validates that the current record's price is a positive value. 
     * 
     * @param fileName
     * @param currentLine
     * @throws SemanticException
     */
    private static void validatePrice(String fileName, String currentLine) throws SemanticException {
        String [] record = currentLine.split(commaRegex);
        
        if (Double.parseDouble(record[2]) < 0) {
            throw new BadPriceException(fileName, currentLine);
        }
    }

    /**
     * Validates that the current records year falls between [1995-2010].
     * 
     * @param fileName
     * @param currentLine
     * @throws SemanticException
     */
    private static void validateYear(String fileName, String currentLine) throws SemanticException {
        String [] record = currentLine.split(commaRegex);
        int year = Integer.parseInt(record[5]);
        
        if (year < 1995 || year > 2010) {
            throw new BadYearException(fileName, currentLine);
        }
    }
    
    /**
     * Closes the ObjectOutputStream of the passed OutputFile.
     * 
     * @param file
     */
    private static void closeObjectOutputStream(OutputFile file) {
        try {
            file.binaryOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*  
    _____           _     ___  
    |  __ \         | |   |__ \ 
    | |__) |_ _ _ __| |_     ) |
    |  ___/ _` | '__| __|   / / 
    | |  | (_| | |  | |_   / /_ 
    |_|   \__,_|_|   \__| |____|                         
    */    

    /**
     * Validates semantics, read the genre files each into arrays of Book objects, then serialize the arrays of Book objects each into binary files.
     */
    private static void do_part2() {
        // Open file containing the names of the output files
        Scanner outputFileNameReader = readFile(outputFilesPath);
        if (outputFileNameReader == null) {
            System.out.println("The file containing the names of the genres does not exist. Terminating program.");
            System.exit(0);
        }
        // Parse the file containing the outputFiles and store them in an array (excludes the BAD_SYN genre file)
        int genreCount = Integer.parseInt(outputFileNameReader.nextLine()) - 1; // the -1 removes the syntax error file from the list
        String[][] outputFileInfo = getOutputFilesInfo(outputFileNameReader, genreCount); // Format: [Name, Genre, FileName]
        
        // Create the outputFiles
        OutputFile[] outputFiles = createOutputFiles(outputFileInfo);

        // Add the semantic error text file to the array of output Objects
        outputFiles = Arrays.copyOf(outputFiles, outputFiles.length + 1);
        outputFiles[outputFiles.length - 1] = new OutputFile("Bad CSV-formatted Output Records", "BAD_SEM", writeFileDirectory + "semantic_error_file.txt");
        
        // Open the ObjectOutputStreams of the OutputFile objects
        for (OutputFile file:outputFiles) {
            if (file.genre.equals("BAD_SEM")) {continue;}
            file.prepareBinaryOutputFile(".ser","/Binary/");    // Modifies the outputFiles' path to that of a bianry file in the Binary directory
            try {
                file.binaryOutputStream = new ObjectOutputStream(new FileOutputStream(file.binaryPath));
            } catch (IOException e) {
                System.out.println("Output file: " + file.binaryPath + " had a problem with its file output");
            }
        }

        // Open the outputStream for the semantic error file
        outputFiles[outputFiles.length - 1].outputStream = writeFile(outputFiles[outputFiles.length - 1].path, false);

        // Read each output file one by one and create Book objects after validating the semantics of their entries. If there is a semantic error, log the error to semantic_error_file.txt
        String currentLine;
        for (OutputFile file:outputFiles) {
            if (file.genre.equals("BAD_SEM")) {continue;}   // Skip the error file because it dosen't contain any books
            Scanner read = readFile(file.path);
            // Read through the entire currently opened file
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
                file.addBook(new Book(entry[0], entry[1], Double.parseDouble(entry[2]), entry[3], Integer.parseInt(entry[5]))); // Book objects don't have Genre attributes, so skip it.
            }
            // Serialize the array of Books to their respective binary files
            try {
                file.binaryOutputStream.writeObject(file.books);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close ObjectOutputStream for current file
                closeObjectOutputStream(file);
            }
        }

        // Close outputStream of semantic error file
        outputFiles[outputFiles.length - 1].outputStream.close();
    }
    
/*
  _____           _     ____        _    _      _                   ______                _   _                 
 |  __ \         | |   |___ \   _  | |  | |    | |                 |  ____|              | | (_)                
 | |__) |_ _ _ __| |_    __) | (_) | |__| | ___| |_ __   ___ _ __  | |__ _   _ _ __   ___| |_ _  ___  _ __  ___ 
 |  ___/ _` | '__| __|  |__ <      |  __  |/ _ \ | '_ \ / _ \ '__| |  __| | | | '_ \ / __| __| |/ _ \| '_ \/ __|
 | |  | (_| | |  | |_   ___) |  _  | |  | |  __/ | |_) |  __/ |    | |  | |_| | | | | (__| |_| | (_) | | | \__ \
 |_|   \__,_|_|   \__| |____/  (_) |_|  |_|\___|_| .__/ \___|_|    |_|   \__,_|_| |_|\___|\__|_|\___/|_| |_|___/
                                                 | |                                                            
                                                 |_|                                                            
 */

    /**
    * Returns an open ObjectInputStream for the passed file path.  
    * 
    * @param path
    * @return ObjectInputStream
    */
    private static ObjectInputStream readBinaryFile(String path) {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(path));
        } catch(FileNotFoundException e) {
            System.out.println("Couldn't find file: " + path + ". Terminating Program.");
            System.exit(0);
        } catch(IOException e) {
            System.out.println("IO Exception for file: " + path + ". Terminating Program.");
            System.exit(0);
        }
        return input;
    }

    /**
     * Displays the Main Menu to the user through the console. No other functionality.
     * 
     * @param currentFile
     */
    public static void displayMenu(OutputFile currentFile) {
        System.out.println("---------------------------------------------------------------------------------------\n" 
                          +"|                                       Main Menu                                     |\n"
                          +"---------------------------------------------------------------------------------------");
        System.out.printf(" v  View the selected file: %s\n", currentFile.binaryPath);
        System.out.println(" s  Select a file to view\n"
                          +" x  Exit\n"
                          +"---------------------------------------------------------------------------------------");
    }

    /**
     * Creates a Scanner object with the System.in stream, prompt the user with the passed message, and return the user's input.
     * 
     * @param message
     * @return String of users input (1 line only)
     */
    private static String getUserInput(String message) {
        Scanner kb = new Scanner(System.in);
        System.out.print("\n" + message);
        return kb.nextLine();
    }

    /**
     * Dilsplays the File Sub-Menu and all of the OutputFiles a user can choose to navigate. Prompts the user for a number corresponding to
     * the file they want to navigate. Validates that the user's input corresponds to one of the available options. 
     * If the user enters a number corresponding to 1 + the total number of available files, 
     * the Sub-Menu will exit and return to the Main Menu. 
     * 
     * @param outputFiles
     * @param currentFileIndex
     * @return updated currentFileIndex (corresponding to user input)
     */
    private static int selectFile(OutputFile[] outputFiles, int currentFileIndex) {
        System.out.println("---------------------------------------------------------------------------------------\n" 
                          +"|                                     File Sub-Menu                                   |\n"
                          +"---------------------------------------------------------------------------------------");
        for (int i=0; i < outputFiles.length; i++) {
            System.out.printf(" %-2d %-68s (%d records)%n", i+1, outputFiles[i].binaryPath, outputFiles[i].entryCount);
            if (i == outputFiles.length - 1) {
                System.out.println(" 9  Exit");
            }
        }
        System.out.println("---------------------------------------------------------------------------------------");

        int newFileIndex = -1;
        do {
            try {
                newFileIndex = Integer.parseInt(getUserInput("Enter Your Choice: ")) - 1; // Index start at 0. The menu starts at 1. Therfore subtract 1 from the index
                if (newFileIndex == outputFiles.length) {
                    newFileIndex = currentFileIndex;   // User exit the selection, keep the file index the same
                } else if (newFileIndex < 0 || newFileIndex > outputFiles.length) {
                    System.out.print("The command you entered isn't in the list of valid commands. Please re-enter your command: ");
                    newFileIndex = -1; // Set newFileIndex to the sentry value
                }
            } catch (NumberFormatException e) {
                System.out.println("Your input isn't an integer. Try again.");
            }
        } while (newFileIndex == -1);
    
        return newFileIndex; 
    }

    /**
     * Prompts the user to enter the number of Book objects they want displayed. Validates that the user's input is a integer.
     * 
     * @return integer corresponding to how many records the user want to display.
     */
    private static int validateNumber() {
        boolean valid = false;
        int n = 0;
        while (!valid) {
            try {
                n = Integer.parseInt(getUserInput("Enter the number of entries you would like to see from the current entry (can be positive or negative). If 0, View Mode will exit: "));
                valid = true;
            } catch(NumberFormatException e) {
                System.out.println("Your input isn't an integer. Try again.");
            }
        }
        return n;
    }

    /**
     * Calls validateNumber() to get the number of Book objects the user wants displayed in the OutputFile at the currentFileIndex then displayes them.
     * The number of Books to be displayed corresponds to the number (n) inputed by the user. The first book displayed will always be Book at the currentBookIndex, followed by the n-1 Books below or above it in the array, depending on if n is positive or negative. 
     * If the end or begining of the array is reached, and appropriate error message will be displayed to the console and the currentBookIndex will correspond to either the last or first book in the array, respectively.
     * The user will get re-prompted as long as they don't enter 0, which will return the user back to the Main Menu.
     * 
     * @param OutputFile
     * @param currentFileIndex
     * @param currentBookIndex
     */
    private static void viewFile(OutputFile[] OutputFile, int currentFileIndex, int currentBookIndex) {
        System.out.printf("Viewing: %s (%d records)%n", OutputFile[currentFileIndex].binaryPath, OutputFile[currentFileIndex].entryCount);
        
        int n;
        do {
            System.out.print("\nCurrent index: " + currentBookIndex);

            // Validate and get number of books user wants to see
            n = validateNumber();
    
            // Evaluate and show the books to the user
            if (n > 0) {
                for (int i=currentBookIndex; i <= currentBookIndex + (n - 1); i++) {
                    try {
                        System.out.println("Index "+ i + ": " + OutputFile[currentFileIndex].books[i]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("EOF has been reached");
                        currentBookIndex = i - 1;   // Set current book to the one just before the error
                        break; 
                    }
                    // When we are on the last iteration of the loop and we are not outOfBounds, update the currentBookIndex
                    if (i == currentBookIndex + (n - 1)) {
                        currentBookIndex = i;
                        break;
                    }
                }
            } else if (n < 0) {
                for (int i=currentBookIndex; i >= currentBookIndex - (Math.abs(n) - 1); i--) {
                    try {
                        System.out.println("Index "+ i + ": " + OutputFile[currentFileIndex].books[i]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("BOF has been reached");
                        currentBookIndex = i + 1; // Set current book to the one just before the error (+ because i--)
                        break;
                    }
                    if (i == currentBookIndex - (Math.abs(n) - 1)) {
                        currentBookIndex = i;
                        break;
                    }
                }
            }
        } while(n !=0);
        System.out.println("\nExiting View Mode.\n");
    }

    /**
     * Takes in an array of OutputFiles and runs the visual navigation Menu for the user.
     * Will end the program if the user enters the value x (not case sensitive).
     * 
     * @param outputFiles
     */
    public static void runMenu(OutputFile[] outputFiles) {
        String userInput;
        int currentFileIndex = 0;
        int currentBookIndex;
        System.out.println("\nWelcome Your Filtered Book Catalogue.\n"
                         + "Please enter one of the commands below to navigate our systems:");
        
        displayMenu(outputFiles[currentFileIndex]);
        do {
            userInput = getUserInput("Enter Your Choice: ").toLowerCase();
            
            switch(userInput) {
            // View Selected file
            case "v":
                currentBookIndex = 0; // When entering the book array, the initial index is always 0
                viewFile(outputFiles, currentFileIndex, currentBookIndex);
                displayMenu(outputFiles[currentFileIndex]);
                break;
            // Select a file to view
            case "s":
                currentFileIndex = selectFile(outputFiles, currentFileIndex);
                displayMenu(outputFiles[currentFileIndex]);
                break;
            // Exit Program
            case "x":
                break;
            default:
                System.out.print("That is not a valid command. Please re-enter a valid valid command from the list above.");
                break;
            }

        } while (!userInput.equals("x"));
        
        System.out.println("Thank you for using the Filtered Book Catalogue!");
    }

    /*  
    _____           _     ____  
    |  __ \         | |   |___ \ 
    | |__) |_ _ _ __| |_    __) |
    |  ___/ _` | '__| __|  |__ < 
    | |  | (_| | |  | |_   ___) |
    |_|   \__,_|_|   \__| |____/                        
    */ 

    /**
     * Reads the binary files, deserialize the array objects in each file and stores them in their corresponding OutputFile, then provides an interacive program to allow the user to navigate the Book arrays of the OutputFiles.
     */
    private static void do_part3() {
        // Open file containing the names of the output files
        Scanner outputFileNameReader = readFile(outputFilesPath);
        if (outputFileNameReader == null) {
            System.out.println("The file containing the names of the genres does not exist. Terminating program.");
            System.exit(0);
        }

        // Parse the file containing the outputFiles and store them in an array (excludes the BAD_SYN genre file)
        int genreCount = Integer.parseInt(outputFileNameReader.nextLine()) - 1; // the -1 removes the syntax error file from the list
        String[][] outputFileInfo = getOutputFilesInfo(outputFileNameReader, genreCount); // Format: [Name, Genre, FileName]

        // Create the outputFiles
        OutputFile[] outputFiles = createOutputFiles(outputFileInfo);

        // Sequentially for each outputFile, convert the path to that of the binary file and open an ObjectInputStream to read the book data and store them in a Book array contained in the OutputFile object
        for (OutputFile file:outputFiles) {
            file.prepareBinaryOutputFile(".ser","/Binary/");
            ObjectInputStream read = readBinaryFile(file.binaryPath);
            // Read array of Book objects stored in the binary file. Only 1 read is requiered to get the entire array
            try {
                file.books = (Book[]) read.readObject(); 
                // Get the enrtyCount for each outputFile by setting it to the length of the book array
                file.entryCount = file.books.length;
            } catch (IOException e) {
                System.out.println("File: " + file.binaryPath + " encountered an IO Exception. Terminating");
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("File: " + file.binaryPath + " dosen't contain an array of Book objects. Terminating");
                System.exit(0);
            }
        }

        runMenu(outputFiles);
    }

    
    public static void main(String[] args) {
        do_part1(); // validating syntax, partition book records based on genre.
        
        do_part2(); // validating semantics, read the genre files each into arrays of Book objects, then serialize the arrays of Book objects each into binary files.
        
        do_part3(); // reading the binary files, deserialize the array objects in each file, and then provide an interacive program to allow the user to navigate the arrays.
    }
}
