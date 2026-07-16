
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents an Employee entity with name, age, department, and salary.
 */
class Employee {
    private final String name;
    private final int age;
    private final String department;
    private final double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

/**
 * Main application class for data manipulation using Streams and Functional Interfaces.
 */
public class EmployeeAnalytics {

    public static void main(String[] args) {
        // 1. Initialize and store the dataset in a collection
        List<Employee> dataset = new ArrayList<>();
        dataset.add(new Employee("Alice Smith", 28, "Engineering", 85000));
        dataset.add(new Employee("Bob Jones", 35, "Marketing", 62000));
        dataset.add(new Employee("Charlie Brown", 42, "Engineering", 110000));
        dataset.add(new Employee("Diana Prince", 31, "HR", 75000));
        dataset.add(new Employee("Evan Wright", 25, "Design", 54000));

        System.out.println("--- Employee Analytics System --- \n");

        // 2. Define the Function interface to map Employee -> "Name [Department]"
        Function<Employee, String> nameAndDeptFormatter = emp -> 
                emp.getName() + " [" + emp.getDepartment() + "]";

        // Age threshold criteria for filtering
        final int ageThreshold = 30;

        // 3 & 5. Stream Pipeline: Filter by age, then transform using the Function interface
        // Demonstrates lazy evaluation (mapping only happens to elements that pass the filter)
        List<String> filteredProfiles = dataset.stream()
                .filter(emp -> emp.getAge() > ageThreshold)
                .map(nameAndDeptFormatter)
                .collect(Collectors.toList());

        System.out.println("Employees older than " + ageThreshold + ":");
        filteredProfiles.forEach(profile -> System.out.println(" - " + profile));

        // 4. Calculate the average salary of ALL employees using stream's built-in functions
        // mapToDouble uses primitive streams to optimize performance and minimize memory usage
        OptionalDouble averageSalary = dataset.stream()
                .mapToDouble(Employee::getSalary)
                .average();

        if (averageSalary.isPresent()) {
            System.out.printf("\nAverage Salary of all employees: $%,.2f%n", averageSalary.getAsDouble());
        } else {
            System.out.println("\nNo data available to calculate average salary.");
        }

        // =====================================================================
        // BONUS FEATURE: Short-Circuiting Performance Optimization
        // Checks if any employee earns over a specific budget threshold.
        // =====================================================================
        double highSalaryCap = 100000.0;
        boolean hasExtremelyHighEarners = dataset.stream()
                .anyMatch(emp -> emp.getSalary() >= highSalaryCap);

        System.out.println("\n--- Performance Optimization Report ---");
        System.out.println("Does the organization have high earners (>= $100k)? " + hasExtremelyHighEarners);
        System.out.println("[Note: Evaluated using 'anyMatch'. Processing stops the instant a match is found.]");
    }
}

