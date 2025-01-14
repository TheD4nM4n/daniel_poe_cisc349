public class Contractor extends PartTimeEmployee {
  
  public Contractor(String name, String title, String ssn, int wage, int hours)
  {
    super(name, title, ssn, wage, hours);
  }

  public void setWage(int wage)
  {
    this.setSalary(wage);
  }

  public int getWage() {
    return this.getSalary();
  }
  
}