public class PartTimeEmployee extends Employee {
  private int hours;
  
  public PartTimeEmployee(String name, String title, String ssn, int wage, int hours) {
    super(name, title, ssn, wage);
    this.hours = hours;
  }

  public void setWeeklyHours(int hours) {
    this.hours = hours;
  }
  
  public int getWeeklyHours() {
    return hours;
  }
}