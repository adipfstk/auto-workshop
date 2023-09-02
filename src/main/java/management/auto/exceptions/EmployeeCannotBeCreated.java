package management.auto.exceptions;

public class EmployeeCannotBeCreated extends RuntimeException {
    public EmployeeCannotBeCreated(String message) {
        super(message);
    }
}
