package Technology;

import InnovationLab.*;

public class SoftwareDeveloper extends Employee {
	private String[] newSkills = { "SQL" };

	public SoftwareDeveloper() {
		super();
		this.salary += 40000;
		this.esop += (super.esop) * 0.05;
		this.setSkills(newSkills);
	}

	public String toString() {
		return "SoftwareDeveloper{" + "}, " + super.toString();
	}
}
