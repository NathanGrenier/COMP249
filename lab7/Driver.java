package lab7;

public class Driver {
	public static void main(String[] args) {
		Computer c1 = new Computer("Intel", 3200, 10);
		Computer c2 = new Computer("AMD", 2400, 100);
		ClassHandler handler = new ClassHandler(c1, "./info.txt");

		handler.writeToFile("");
		System.out.println(handler.readFromFile());
	}
}
