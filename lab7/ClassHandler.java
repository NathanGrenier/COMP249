package lab7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClassHandler {
	Computer computer;
	String path;

	public ClassHandler(Computer computer, String path) {
		this.computer = computer;
		this.path = path;
	}

	// The path passed into this function is not used. Uses the classes path
	// instead. Did this because the quesion only requiers a specified path when
	// writing a
	// file, but not when reading
	public void writeToFile(String path) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream(this.path));

			pw.printf("CPU: %s\n", computer.CPU);
			pw.printf("RAM: %d\n", computer.RAM);
			pw.printf("Storage: %d\n", computer.storage);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found.");
			System.exit(0);
		} finally {
			pw.close();
		}
	}

	public Computer readFromFile() {
		String CPU = "";
		int RAM = -1;
		int storage = -1;

		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new FileInputStream(this.path));

			CPU = inputStream.nextLine().split(":")[1].trim();
			RAM = Integer.parseInt(inputStream.nextLine().split(":")[1].trim());
			storage = Integer.parseInt(inputStream.nextLine().split(":")[1].trim());
		} catch (FileNotFoundException e) {
			System.out.println("File not Found (to read).");
			System.exit(0);
		} finally {
			inputStream.close();
		}

		return new Computer(CPU, RAM, storage);
	}
}
