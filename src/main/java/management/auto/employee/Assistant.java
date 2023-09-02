package management.auto.employee;

public class Assistant extends Employee {
    public Assistant(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder.getEmployeeDetails());
        this.setSalaryCoefficient(1.0);
    }
}
