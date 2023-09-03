package management.auto.menu.Menu;

import management.auto.car.Bus;
import management.auto.car.Car;
import management.auto.car.StandardCar;
import management.auto.car.Truck;
import management.auto.db.WorkShop;
import management.auto.employee.Employee;
import management.auto.employee.Mechanic;

import java.util.List;

public class WorkshopMenu {

    private final List<Employee> employees = WorkShop.getInstance().getEmployees();
    private void displayMechanicsByAvailSts() {
        System.out.println("Id    Name    Surname    StandardVehicles    BigVehicles");
        employees
                .stream()
                .filter(employee -> employee instanceof Mechanic)
                .forEach(mechanic -> ((Mechanic) mechanic).getStatus());
    }

    private void assignACar(int id) {
        System.out.println("What type of car do you repair [1-standard, 2 - truck, 3 - bus]");
        Car car = null;
        int option = InputUtil.scanner.nextInt();
        switch (option){
            case 1 -> car = new StandardCar();
            case 2 -> car = new Truck();
            case 3 -> car = new Bus();
        }
        if (car!=null)
            ((Mechanic) this.employees.get(id)).addCar(car);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("Workshop Menu:");
            System.out.println("1 - Display mechanics by availability");
            System.out.println("2 - Assign a car to a mechanic");
            System.out.println("Any - Exit");
            int option = InputUtil.readInput("Enter option: ");

            if (option == 1) {
                this.displayMechanicsByAvailSts();
            }

            else if (option == 2) {
                int id = InputUtil.readInput("Enter mechanic ID: ");
                this.assignACar(id);
            }
            else {
                break;
            }

        }
    }
}
