package Technology;
import InnovationLab.*;

public class Driver {

	public static void main(String[] args) {
		SoftwareDeveloper dev1 = new SoftwareDeveloper();
		SoftwareDeveloper dev2 = new SoftwareDeveloper();
		DevOps devop1 = new DevOps();
		DevOps devop2 = new DevOps();
		
		Employee[] array = {dev1, dev2, devop1, devop2};
		for (Employee employee: array) {
			System.out.println(employee);
		}
	}
}
