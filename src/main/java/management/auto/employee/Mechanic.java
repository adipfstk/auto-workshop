package management.auto.employee;

import management.auto.car.Car;
import management.auto.car.StandardCar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mechanic extends Employee {
    private final Task task;
    private boolean standardAvailable;
    private boolean otherAvailable;
    private final ExecutorService executorService;
    private boolean free = true;

    public Mechanic(EmployeeBuilder employeeBuilder) {
        super(employeeBuilder.getEmployeeDetails());
        this.setSalaryCoefficient(1.5);
        this.task = new Task();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void addCar(Car car) {
        if (free) {
            this.executorService.submit(this.task);
            free = false;
        }
        var standardCarCount = this.task.getStandardCarCount();
        var otherCarCount = this.task.getOtherCarCount();
        var mechanicQueue = this.task.getCarQueue();
        if (car instanceof StandardCar && standardCarCount < 3) {
            mechanicQueue.add(car);
            this.task.setStandardCarCount(standardCarCount + 1);
        } else if (otherCarCount < 1) {
            mechanicQueue.add(car);
            this.task.setOtherCarCount(otherCarCount + 1);
        } else {
            System.out.println("Cannot add any more. The mechanic is full at this moment");
        }
    }

    public void getStatus() {
        this.updateStatus();
        System.out.println(
                this.getIdCopy()
                        + "    "
                        + this.getName()
                        + "    "
                        + this.getSurname()
                        + "    "
                        + this.standardAvailable
                        + "    "
                        + this.otherAvailable
        );
    }

    private void updateStatus() {
        this.otherAvailable = this.task.getOtherCarCount() == 0;
        this.standardAvailable = this.task.getStandardCarCount() < 3;
    }

    public boolean isStandardAvailable() {
        return standardAvailable;
    }

    public boolean isOtherAvailable() {
        return otherAvailable;
    }

}
