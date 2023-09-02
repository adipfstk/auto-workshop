package management.auto.employee;

public class Director extends Employee {
    public Director(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder.getEmployeeDetails());
        this.setSalaryCoefficient(2.0);
    }
}
