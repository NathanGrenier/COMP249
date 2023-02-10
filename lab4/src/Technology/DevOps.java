package Technology;
import InnovationLab.*;

public class DevOps extends Employee{
	private String[] newSkills = {"AWS"};
	
	public DevOps() {
		super();
		this.salary -= (super.salary)*0.2;
		this.esop -= (super.esop)*0.02;
		this.setSkills(newSkills);
	}
	
	public String toString() {
		return "DevOps{" + "}, " + super.toString();
	}
}
