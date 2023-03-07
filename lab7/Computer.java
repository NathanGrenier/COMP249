package lab7;

public class Computer {
	String CPU;
	int RAM;
	int storage;

	public Computer() {
		this.CPU = "default";
		this.RAM = 0;
		this.storage = 0;
	}

	public Computer(String CPU, int RAM, int storage) {
		this.CPU = CPU;
		this.RAM = RAM;
		this.storage = storage;
	}

	public String toString() {
		return "Computer{CPU=" + this.CPU + ", RAM=" + this.RAM + ", storage=" + this.storage + "}";
	}

	public String getCPU() {
		return this.CPU;
	}

	public int getRAM() {
		return this.RAM;
	}

	public int getStorage() {
		return this.storage;
	}

	public void setCPU(String CPU) {
		this.CPU = CPU;
	}

	public void setRAM(int RAM) {
		this.RAM = RAM;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}
}
