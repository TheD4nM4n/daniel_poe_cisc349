public class Employee {
  private String name;
  private String title;
  private String ssn; // this is so incredibly insecure, but it's just an example
  private int salary; 

  public Employee(String name, String title, String ssn, int salary) {
    this.name = name;
    this.title = title;
    this.ssn = ssn;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public void setName() {
    this.name = name;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }
  
  public int getSalary() {
    return salary;
  }
}