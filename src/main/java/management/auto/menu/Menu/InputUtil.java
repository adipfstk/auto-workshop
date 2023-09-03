package management.auto.menu.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {
    public static final Scanner scanner = new Scanner(System.in);
    public static int readInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid type.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }
}
