package Service;

import Entities.Product;
import java.util.Scanner;

public class ProductService {

    protected static void editCommonFields(Product product, Scanner input) {
        System.out.println("1. Edit Name");
        System.out.println("2. Edit Brand");
        System.out.println("3. Edit ID");
        System.out.println("4. Edit Color");
        System.out.println("5. Edit Price");
        System.out.println("6. Edit Stock");
        System.out.println("7. Edit Status");
        System.out.println("0. Finish Editing");

        int choice;
        do {
            choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter new name: ");
                    product.setName(input.nextLine());
                    break;
                case 2:
                    System.out.println("Enter new brand: ");
                    product.setBrand(input.nextLine());
                    break;
                case 3:
                    System.out.println("Enter new ID: ");
                    product.setId(input.nextLine());
                    break;
                case 4:
                    System.out.println("Enter new color: ");
                    product.setColor(input.nextLine());
                    break;
                case 5:
                    System.out.println("Enter new price: ");
                    product.setPrice(Integer.parseInt(input.nextLine()));
                    break;
                case 6:
                    System.out.println("Enter new stock: ");
                    product.setStock(Integer.parseInt(input.nextLine()));
                    break;
                case 7:
                    System.out.println("Enter new status: ");
                    product.setStatus(input.nextLine());
                    break;
                case 0:
                    System.out.println("Finished editing common fields.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}