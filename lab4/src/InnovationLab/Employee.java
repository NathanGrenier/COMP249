package InnovationLab;
import java.util.Arrays;

public class Employee {
	protected String[] skills;
	protected double salary;
	protected double esop;
	
	public Employee() {
		this.skills = new String[2];
		this.skills[0] = "java";
		this.skills[1] = "python";
		this.salary = 40000;
		this.esop = 2;
	}
	
	public String toString() {
		String skillsString = "[";
		for (String skill: this.skills) {
			skillsString += skill + ", ";
		}
		skillsString += "]";
		return "Employee{Skills=" + skillsString + ", Salary=" + this.salary + ", ESOP=" + this.esop + "}";
	}
	
	// getters
	public String[] getSkills() {
		return this.skills;
	}
	public double getSalary() {
		return this.salary;
	}
	public double getEsop() {
		return this.esop;
	}
	
	//setters
	public void setSkills(String[] skills) {
		for (String skill: skills) {
			this.skills = Arrays.copyOf(this.skills, this.skills.length + 1);
			this.skills[this.skills.length - 1] = skill;
		}
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void setEsop(double esop) {
		this.esop = esop;
	}
}
