package Service;

import java.util.Scanner;

public class ProductService {
    protected static boolean editStringField(String fieldName, String currentValue, Scanner input) {
        System.out.print("Enter new " + fieldName + " (or press Enter to skip): ");
        String newValue = input.nextLine().trim();
        if (!newValue.isEmpty() && !newValue.equals(currentValue)) {
            System.out.println(fieldName + " updated successfully.");
            return true;
        }
        return false;
    }

    protected static boolean editIntField(String fieldName, int currentValue, Scanner input) {
        System.out.print("Enter new " + fieldName + " (or press Enter to skip): ");
        String newValue = input.nextLine().trim();
        if (!newValue.isEmpty()) {
            try {
                int intValue = Integer.parseInt(newValue);
                if (intValue != currentValue) {
                    System.out.println(fieldName + " updated successfully.");
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return false;
    }
}