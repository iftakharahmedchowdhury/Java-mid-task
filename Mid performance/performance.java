import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

class Employee {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private LocalDate joiningDate;

    public Employee(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
    }

   public int calculateVacationLeave() {
       int totalLeaveDays = calculateTotalLeaveDays();
       int vacationDays = getVacationDays();
       return (int) Math.ceil((double) vacationDays * totalLeaveDays / 365);
   }
    public int calculateSickLeave() {
        int totalLeaveDays = calculateTotalLeaveDays();
        int sickDays = getSickDays();
        if (this instanceof Staff) {
            return sickDays < 0.5 ? 0 : 1;
        } else {
            return (int) Math.ceil((double) sickDays * totalLeaveDays / 365);
        }
    }

    private int calculateTotalLeaveDays() {
        LocalDate endOfYear = LocalDate.of(joiningDate.getYear(), 12, 31);
        int totalDaysInYear = joiningDate.isLeapYear() ? 366 : 365;

        if (Period.between(joiningDate, endOfYear).getYears() == 0) {
            return (int)((endOfYear.getDayOfYear() - joiningDate.getDayOfYear() + 1) * totalDaysInYear / 365.0);
        } else {
            return (int)((endOfYear.getDayOfYear() + 1) * totalDaysInYear / 365.0);
        }
    }

    private int getVacationDays() {
        return this instanceof Officer ? 15 : 10;
    }

    private int getSickDays() {
        return this instanceof Officer ? 10 : 7;
    }

    public String toString() {
        return "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Email: " + email + "\n" +
                "Joining Date: " + joiningDate + "\n" +
                "Vacation Leave: " + calculateVacationLeave() + "\n" +
                "Sick Leave: " + calculateSickLeave() + "\n";
    }
}

class Officer extends Employee {
    public Officer(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
        super(id, name, dateOfBirth, email, joiningDate);
    }
}

class Staff extends Employee {
    public Staff(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
        super(id, name, dateOfBirth, email, joiningDate);
    }
}

public class performance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee[] employees = new Employee[3];

        for (int i = 0; i < employees.length; i++) {
            System.out.println("Enter Employee Information:");

            System.out.print("ID: ");
            int id = scanner.nextInt();

            System.out.print("Name: ");
            String name = scanner.next();

            System.out.print("Date of Birth (yyyy-MM-dd): ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.next());

            System.out.print("Email: ");
            String email = scanner.next();

            System.out.print("Joining Date (yyyy-MM-dd): ");
            LocalDate joiningDate = LocalDate.parse(scanner.next());

            System.out.print("Employee Type (1 for Officer, 2 for Staff): ");
            int employeeType = scanner.nextInt();

            Employee employee;
            if (employeeType == 1) {
                employee = new Officer(id, name, dateOfBirth, email, joiningDate);
            } else {
                employee = new Staff(id, name, dateOfBirth, email, joiningDate);
            }

            employees[i] = employee;
        }

        System.out.println("\nEmployee Details and Leave Information:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
