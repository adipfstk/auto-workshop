package management.auto.employee;

import management.auto.exceptions.*;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeBuilder {
    private final EmployeeDetails employeeDetails;

    public EmployeeBuilder() {
        employeeDetails = new EmployeeDetails();
    }

    public EmployeeBuilder withName(String name) {
        if (isValidName(name)) {
            employeeDetails.setName(name);
        } else {
            throw new InvalidNameException("Invalid name: should have a length between 1 and 30 characters");
        }
        return this;
    }

    public EmployeeBuilder withSurname(String surname) {
        if (isValidSurname(surname)) {
            employeeDetails.setSurname(surname);
        } else {
            throw new InvalidSurnameException("Invalid surname: should have a length between 1 and 30 characters");
        }
        return this;
    }

    public EmployeeBuilder withBirthDate(LocalDate birthDate) {
        if (isValidBirthDate(birthDate)) {
            employeeDetails.setBirthDate(birthDate);
        } else {
            throw new InvalidBirthDateException("Invalid birth date: an employee should be at least 18 years old.");
        }
        return this;
    }

    public EmployeeBuilder withEmpDate(LocalDate empDate) {
        if (isValidEmpDate(empDate)) {
            employeeDetails.setEmpDate(empDate);
        } else {
            throw new InvalidEmpDateException("Invalid employee date: must not be in the future");
        }
        return this;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public Employee build(int type) throws InvalidOptionException {
        validateEmployeeData();
        Employee.id++;
        return switch (type) {
            case 1 -> new Director(this);
            case 2 -> new Mechanic(this);
            case 3 -> new Assistant(this);
            default ->
                    throw new InvalidOptionException("Please select one of the following options [1- Director, 2- Mechanic, 3-Assistant]");
        };
    }

    private void validateEmployeeData() throws InvalidDataInputException {
        if (!isValidBirthDate(employeeDetails.getBirthDate())
                || !isValidEmpDate(employeeDetails.getEmpDate())
                || !isValidName(employeeDetails.getName())
                || !isValidSurname(employeeDetails.getSurname())) {
            throw new InvalidDataInputException("Invalid data input!");
        }
    }

    private boolean isValidBirthDate(LocalDate birthDate) {
        LocalDate empDate = this.employeeDetails.getEmpDate();
        return Period.between(birthDate, empDate).getYears() >= 18;
    }

    private boolean isValidEmpDate(LocalDate empDate) {
        return !empDate.isAfter(LocalDate.now());
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.length() <= 30;
    }

    private boolean isValidSurname(String surname) {
        return surname != null && !surname.isEmpty() && surname.length() <= 30;
    }
}
