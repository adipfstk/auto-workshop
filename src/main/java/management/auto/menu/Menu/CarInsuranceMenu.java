package management.auto.menu.Menu;

import management.auto.car.Car;
import management.auto.car.StandardCar;
import management.auto.car.Truck;

import static management.auto.menu.Menu.InputUtil.readInput;

public class CarInsuranceMenu {

    public void calculateInsuranceCost(Car car, boolean discount) {
        System.out.println(car.calcInsuranceCost(discount));
    }

    public Car enterCarDetails(Car car) {
        car.setDiesel(readInput("Is the car a diesel [1-true] [any-false]? ") == 1);
        car.setKmCnt(readInput("Enter the number of km: "));
        car.setConstructionYear(readInput("Enter the construction year of: "));
        return car;
    }

    public void displayMenu() {
        EmployeeManagementMenu employeeManagementMenu = new EmployeeManagementMenu();
        WorkshopMenu workshopMenu = new WorkshopMenu();
        while (true) {
            System.out.println("Car insurance menu");
            System.out.println("===================");
            System.out.println("Calculate insurance cost for given categories:");
            System.out.println("1 - Standard car");
            System.out.println("2 - Truck");
            System.out.println("3 - Bus");
            System.out.println("Any - Exit");
            int option = readInput("Enter option: ");
            if (option == 1) {
                calculateInsuranceCost(enterCarDetails(new StandardCar()), readInput("Do you wanna add discount [1 -yes] [any - no]?: ") == 1);
            } else if (option == 2) {
                calculateInsuranceCost(enterCarDetails(new Truck()), readInput("Do you wanna add discount [1 -yes] [any - no]?: ") == 1);
            } else if (option == 3) {
                calculateInsuranceCost(enterCarDetails(new StandardCar()), readInput("Do you wanna add discount [1 -yes] [any - no]?: ") == 1);
            } else {
                System.out.println("You will be redirected to main menu!");
                break;
            }
        }
    }
}
