import java.io.Serializable;

public class Employer implements Serializable {
    String name;
    int age;
    int salary;
    String company;
    String ID;

    public Employer() {
        this.name = "default";
        this.age = -1;
        this.salary = -1;
        this.company = "default";
        this.ID = "default";
    }

    public String toString() {
        return "Employer{name=" + this.name + ", age=" + this.age + ", salary=" + this.salary + ", company=" + this.company + ", ID=" + this.ID + "}";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getCompany() {
        return company;
    }

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setID(String iD) {
        ID = iD;
    }

}
