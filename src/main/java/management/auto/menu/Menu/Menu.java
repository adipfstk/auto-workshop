package management.auto.menu.Menu;

public class Menu {

    public void displayMenu() {
        EmployeeManagementMenu employeeManagementMenu = new EmployeeManagementMenu();
        WorkshopMenu workshopMenu = new WorkshopMenu();
        while (true) {
            System.out.println("Main menu");
            System.out.println("===================");
            System.out.println("Select an option:");
            System.out.println("1 - Employee management");
            System.out.println("2 - Cars Insurance Management");
            System.out.println("3 - Workshop");
            System.out.println("Any - Exit");
            int option = InputUtil.readInput("Enter option: ");

            if (option == 1) {
                employeeManagementMenu.displayMenu();
                // Add cases for cars management and workshop activities as needed.
            } else if (option == 2) {
                continue;
            } else if (option == 3) {
                workshopMenu.displayMenu();
            } else {
                break;
            }
        }
    }
}
