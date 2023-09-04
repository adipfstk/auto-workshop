package management.auto.employee;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    public static long id;
    private final long idCopy = id - 1;
    private final EmployeeDetails employeeDetails;

    public Employee(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public Double calculateSalary() {
        return Period.between(employeeDetails.getBirthDate(), LocalDate.now())
                .getYears() * 1000 * this.employeeDetails.getSalaryCoefficient();
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Employee.id = id;
    }

    public String getName() {
        return employeeDetails.getName();
    }

    public void setName(String name) {
        this.employeeDetails.setName(name);
    }

    public String getSurname() {
        return this.employeeDetails.getSurname();
    }

    public void setSurname(String surname) {
        this.employeeDetails.setSurname(surname);
    }

    public LocalDate getBirthDate() {
        return this.employeeDetails.getBirthDate();
    }

    public void setBirthDate(LocalDate birthDate) {
        this.employeeDetails.setBirthDate(birthDate);
    }

    public LocalDate getEmpDate() {
        return employeeDetails.getEmpDate();
    }

    public void setEmpDate(LocalDate empDate) {
        this.employeeDetails.setEmpDate(empDate);
    }

    public double getSalaryCoefficient() {
        return this.employeeDetails.getSalaryCoefficient();
    }

    public void setSalaryCoefficient(double salaryCoefficient) {
        this.employeeDetails.setSalaryCoefficient(salaryCoefficient);
    }

    public long getIdCopy() {
        return idCopy;
    }
}
