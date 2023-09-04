package management.auto.menu.Menu;

import management.auto.db.WorkShop;
import management.auto.employee.Employee;
import management.auto.employee.EmployeeBuilder;
import management.auto.exceptions.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class EmployeeManagementMenu {
    private final Scanner scanner = InputUtil.scanner;
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


    private LocalDate readDateInput() {
        int year = InputUtil.readInput("Enter year: ");
        int month = InputUtil.readInput("Enter month: ");
        int day = InputUtil.readInput("Enter day: ");
        return LocalDate.of(year, month, day);
    }

    public void addEmployee() {
        System.out.println("Choose employee type: [1 - Director, 2 - Mechanic, 3 - Assistant]");
        int option = InputUtil.readInput("Enter employee type: ");

        Employee employee = null;
        if (option >= 1 && option <= 3) {
            try {
                employee = configureEmployee(option)
                        .orElseThrow(() ->
                                new EmployeeCannotBeCreated("Employee creation failed because one of the conditions were not met!"));
                WorkShop.getInstance().getEmployees().add(employee);
            } catch (EmployeeCannotBeCreated failure) {
                System.out.println(failure.getMessage());
            }
        }
    }

    private void calcEmpSalaryById(int id) {
        try {
            System.out.println(WorkShop.getInstance().getEmployees().get(id).calculateSalary());
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("User id is not valid");
        }
    }

    private void editEmployeeDetails() {
        System.out.print("Enter Employee ID to find: ");
        long idToFind = scanner.nextLong();
        scanner.nextLine();

        Optional<Employee> foundEmployee = findEmployeeById(idToFind);
        if (foundEmployee.isPresent()) {
            Employee employee = foundEmployee.get();
            System.out.println("Employee found:");
            System.out.println("ID: " + employee.getIdCopy());
            System.out.println("Name: " + employee.getName());

            boolean continueUpdating = true;

            while (continueUpdating) {
                System.out.println("What field do you want to update (name, surname, birthdate, empdate, salary, exit)?");
                String fieldToUpdate = scanner.nextLine().toLowerCase();

                switch (fieldToUpdate) {
                    case "name":
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        employee.setName(newName);
                        System.out.println("Name updated successfully.");
                        break;
                    case "surname":
                        System.out.print("Enter new surname: ");
                        String newSurname = scanner.nextLine();
                        employee.setSurname(newSurname);
                        System.out.println("Surname updated successfully.");
                        break;
                    case "birthdate":
                        System.out.print("Enter new birthdate (yyyy-MM-dd): ");
                        LocalDate newBirthDate = LocalDate.parse(scanner.nextLine());
                        employee.setBirthDate(newBirthDate);
                        System.out.println("Birthdate updated successfully.");
                        break;
                    case "empdate":
                        System.out.print("Enter new employment date (yyyy-MM-dd): ");
                        LocalDate newEmpDate = LocalDate.parse(scanner.nextLine());
                        employee.setEmpDate(newEmpDate);
                        System.out.println("Employment date updated successfully.");
                        break;
                    case "salary":
                        System.out.print("Enter new salary coefficient: ");
                        double newSalaryCoefficient = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        employee.setSalaryCoefficient(newSalaryCoefficient);
                        System.out.println("Salary coefficient updated successfully.");
                        break;
                    default:
                        System.out.println("You sure you wanna exit? [yes - exit, no - continue]");
                        String continueInput = scanner.nextLine().toLowerCase();
                        continueUpdating = !continueInput.equals("yes");
                        break;
                }
            }
        } else {
            System.out.println("Employee not found with ID: " + idToFind);
        }
    }



    private Optional<Employee> findEmployeeById(long id) {
        return WorkShop.getInstance().getEmployees().stream()
                .filter(employee -> employee.getIdCopy() == id)
                .findFirst();
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Employee Management Menu:");
            System.out.println("1 - Add employee");
            System.out.println("2 - Edit employee by ID");
            System.out.println("3 - Calculate employee salary by ID");
            System.out.println("Any - Exit");
            int option = InputUtil.readInput("Enter option: ");

            if (option == 1) {
                addEmployee();
            }
            else if (option == 2) {
                editEmployeeDetails();
            }
            else if (option == 3) {
                var userId = InputUtil.readInput("Enter your desired user id: ");
                calcEmpSalaryById(userId);
            }
            else {
                System.out.println("You just exit from employee management window!");
                break;
            }
        }
    }
}
