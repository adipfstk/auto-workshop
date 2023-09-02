package management.auto.employee;

public class Mechanic extends Employee {
    public Mechanic(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder.getEmployeeDetails());
        this.setSalaryCoefficient(1.5);
    }
}
