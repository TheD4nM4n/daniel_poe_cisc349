
// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;

import java.util.*;
// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) {
    ArrayList<Employee> employees = new ArrayList<Employee>();

    employees.add(new Employee("John Doe", "Manager", "123-45-6789", 70000));
    employees.add(new Employee("Jane Smith", "Engineer", "987-65-4321", 60000));
    employees.add(new Employee("Bob the Builder", "Engineer", "555-55-5555", 640000));
    employees.add(new PartTimeEmployee("Alice Glass", "Engineer", "111-11-1111", 50000, 24));

    Scanner selectionScanner = new Scanner(System.in);
    int selectedEmployee = 1;
    Boolean running = true;

    System.out.println("Welcome to the Employee Management System!");

    while (running) {

      System.out.println("Please type a command:");
      System.out.println("view: view all employees");
      System.out.println("select: select an employee");
      System.out.println("add: add a new employee");
      System.out.println("remove: removes selected employee");
      System.out.println("edit: edits selected employee");
      System.out.println("exit: exits the program");

      String selectedOption = selectionScanner.nextLine();

      if (selectedOption.equals("view")) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (Employee employee : employees) {
          if (employees.indexOf(employee) == selectedEmployee) {
            System.out.println("> " + employees.indexOf(employee) + ": " + employee.getName());
          } else {
            System.out.println("  " + employees.indexOf(employee) + ": " + employee.getName());
          }
        }

      } else if (selectedOption.equals("select")) {
        System.out.println("Please type the number of the employee you would like to select:");
        selectedEmployee = selectionScanner.nextInt();
        System.out.println("You have selected: " + employees.get(selectedEmployee).getName());

      } else if (selectedOption.equals("add")) {
        System.out.println("pick from the following:");
        System.out.println("1. [e]mployee (default)");
        System.out.println("2. [p]art-time");
        System.out.println("3. [c]ontractor");

        int employeeType = 0;

        selectedOption = selectionScanner.nextLine();

        if (selectedOption.equals("p") || selectedOption.equals("part-time")) {
          employeeType = 1;
        } else if (selectedOption.equals("c") || selectedOption.equals("contractor")) {
          employeeType = 2;
        }

        System.out.println("name:");
        String newName = selectionScanner.nextLine();
        System.out.println("title:");
        String newTitle = selectionScanner.nextLine();
        System.out.println("ssn:");
        String newSSN = selectionScanner.nextLine();

        int newSalary;
        int newHours = 0;

        if (employeeType != 2) {
          System.out.println("wage:");
          newSalary = selectionScanner.nextInt();
        } else {
          System.out.println("salary:");
          newSalary = selectionScanner.nextInt();
        }
        if (employeeType != 0) {
          System.out.println("hours this week:");
          newHours = selectionScanner.nextInt();
        }

        switch (employeeType) {
          case 0:
            employees.add(new Employee(newName, newTitle, newSSN, newSalary));
            break;
          case 1:
            employees.add(new PartTimeEmployee(newName, newTitle, newSSN, newSalary, newHours));
            break;
          case 2:
            employees.add(new Contractor(newName, newTitle, newSSN, newSalary, newHours));
            break;
        }
      } else if (selectedOption.equals("remove")) {
        System.out.println("confirm deletion of " +
            employees.get(selectedEmployee).getName() +
            "? [Y/n]");
        selectedOption = selectionScanner.nextLine();
        if (selectedOption.toLowerCase().equals("y") || selectedOption.equals("")) {
          employees.remove(selectedEmployee);
        } else {
          System.out.println("aborted.");
        }
      } else if (selectedOption.equals("edit")) {
        Employee currentEmployee = employees.get(selectedEmployee);
        System.out.println("Editing " + currentEmployee.getName() + ".");
        System.out.println("pick from the following:");
        System.out.println("1. [n]ame");
        System.out.println("2. [t]itle");
        System.out.println("3. [s]sn");
        System.out.println("4. [w]age/salary");
        if (!employees.get(selectedEmployee).getClass().equals(Employee.class)) {
          System.out.println("5. weekly [h]ours");
        }

        selectedOption = selectionScanner.nextLine();

        if (selectedOption.equalsIgnoreCase("n") || selectedOption.equalsIgnoreCase("1")) {
          System.out.println("new name:");
          String newName = selectionScanner.nextLine();
          currentEmployee.setName(newName);
        } else if (selectedOption.equalsIgnoreCase("t") || selectedOption.equalsIgnoreCase("2")) {
          System.out.println("new title:");
          String newTitle = selectionScanner.nextLine();
          currentEmployee.setTitle(newTitle);
        } else if (selectedOption.equalsIgnoreCase("s") || selectedOption.equalsIgnoreCase("3")) {
          System.out.println("new ssn:");
          String newSSN = selectionScanner.nextLine();
          currentEmployee.setSSN(newSSN);
        } else if (selectedOption.equalsIgnoreCase("w") || selectedOption.equalsIgnoreCase("4")) {
          System.out.println("new wage/salary:");
          int newSalary = selectionScanner.nextInt();
          currentEmployee.setSalary(newSalary);
        } else if (selectedOption.equalsIgnoreCase("h") || selectedOption.equalsIgnoreCase("5")) {
          System.out.println("new hours:");
          int newHours = selectionScanner.nextInt();
          ((PartTimeEmployee) currentEmployee).setWeeklyHours(newHours);
        }
      }
      selectionScanner.close();
    }
  }
}