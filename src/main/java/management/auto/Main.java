package management.auto;

import management.auto.db.Database;
import management.auto.employee.Employee;
import management.auto.employee.EmployeeBuilder;
import management.auto.exceptions.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    public Optional<Employee> configureEmployee(int type) {
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();
        System.out.println("Enter the name for your employee: ");
        String name = scanner.next();
        System.out.println("Enter the surname for your employee: ");
        String surname = scanner.next();
        System.out.println("Enter the employee date [yy/mm/dd]: ");
        LocalDate empDate = readDateInput();
        System.out.println("Enter the birthdate for your employee [yy/mm/dd]: ");
        LocalDate birthDate = readDateInput();

        try {
            return Optional.of(
                    employeeBuilder
                            .withSurname(surname)
                            .withName(name)
                            .withEmpDate(empDate)
                            .withBirthDate(birthDate)
                            .build(type)
            );

        } catch (InvalidOptionException | InvalidDataInputException | InvalidBirthDateException |
                 InvalidEmpDateException | InvalidNameException | InvalidSurnameException e) {
            System.out.println("Error: " + e.getMessage());
            return Optional.empty();
        }
    }

    private int readInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid type.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private LocalDate readDateInput() {
        int year = readInput("Enter year: ");
        int month = readInput("Enter month: ");
        int day = readInput("Enter day: ");
        return LocalDate.of(year, month, day);
    }

    public void addEmployee() {
        System.out.println("Choose employee type: [1 - Director, 2 - Mechanic, 3 - Assistant]");
        int option = readInput("Enter employee type: ");

        Employee employee = null;
        if (option >= 1 && option <= 3) {
            try {
                employee = configureEmployee(option)
                        .orElseThrow(() ->
                                new EmployeeCannotBeCreated("Employee creation failed because one of the conditions were not met!"));
            } catch (EmployeeCannotBeCreated failure) {
                System.out.println(failure.getMessage());
            }
        }
    }

    void calcEmpSalaryById(int id) {
        try {
            System.out.println(Database.getInstance().getEmployees().get(id).calculateSalary());
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("User id is not valid");
        }
    }

    public void employeeManagement() {
        while (true) {
            System.out.println("Employee Management Menu:");
            System.out.println("1 - Add employee");
            System.out.println("2 - Edit employee by ID");
            System.out.println("3 - Calculate employee salary by ID");
            System.out.println("Any - Exit");
            int option = readInput("Enter option: ");

            if (option == 1) {
                addEmployee();
                // Add cases for editing and calculating salary by ID as needed.
            }
            else if (option == 3) {
                var userId = readInput("Enter your desired user id: ");
                calcEmpSalaryById(userId);
            }
            else {
                System.out.println("You just exit from employee management window!");
                break;
            }
        }
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Main menu");
            System.out.println("===================");
            System.out.println("Select an option:");
            System.out.println("1 - Employee management");
            System.out.println("2 - Cars management");
            System.out.println("3 - Workshop activities");
            System.out.println("Any - Exit");
            int option = readInput("Enter option: ");

            if (option == 1) {
                employeeManagement();
                // Add cases for cars management and workshop activities as needed.
            } else {
                System.out.println("Thank you for your time! Have a nice day!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.displayMenu();
    }
}
