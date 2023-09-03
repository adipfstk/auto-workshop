package management.auto.db;

import management.auto.car.Car;
import management.auto.employee.Employee;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WorkShop {
    private static WorkShop dbInstance;
    private final List<Employee> employees = new ArrayList<>();

    private final Queue<Car> waitingCarList = new LinkedList<>();

    private WorkShop() {
    }

    public static synchronized WorkShop getInstance() {
        if (dbInstance == null) {
            dbInstance = new WorkShop();
        }
        return dbInstance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Queue<Car> getWaitingCarList() {
        return waitingCarList;
    }
}
