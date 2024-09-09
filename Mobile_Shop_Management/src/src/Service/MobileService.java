package Service;

import Entities.Mobile;
import Factory.MobileFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class MobileService extends ProductService {
    public static void addMobile(ArrayList<Mobile> mobiles, Scanner input) {
        MobileFactory factory = new MobileFactory();
        Mobile newMobile = (Mobile) factory.createProduct(input);
        mobiles.add(newMobile);
        System.out.println("Mobile added successfully.");
    }

    public static boolean editMobile(Mobile mobile, Scanner input) {
        boolean updated = false;
        while (true) {
            System.out.println("\nEdit Mobile Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Brand");
            System.out.println("3. Edit ID");
            System.out.println("4. Edit Color");
            System.out.println("5. Edit Price");
            System.out.println("6. Edit Stock");
            System.out.println("7. Edit Status");
            System.out.println("8. Edit OS");
            System.out.println("0. Finish Editing");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        if (ProductService.editStringField("name", mobile.getName(), input)) {
                            mobile.setName(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 2:
                        if (ProductService.editStringField("brand", mobile.getBrand(), input)) {
                            mobile.setBrand(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 3:
                        if (ProductService.editStringField("ID", mobile.getId(), input)) {
                            mobile.setId(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 4:
                        if (ProductService.editStringField("color", mobile.getColor(), input)) {
                            mobile.setColor(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 5:
                        if (ProductService.editIntField("price", mobile.getPrice(), input)) {
                            mobile.setPrice(Integer.parseInt(input.nextLine().trim()));
                            updated = true;
                        }
                        break;
                    case 6:
                        if (ProductService.editIntField("stock", mobile.getStock(), input)) {
                            mobile.setStock(Integer.parseInt(input.nextLine().trim()));
                            updated = true;
                        }
                        break;
                    case 7:
                        if (ProductService.editStringField("status", mobile.getStatus(), input)) {
                            mobile.setStatus(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 8:
                        if (ProductService.editStringField("OS", mobile.getOs(), input)) {
                            mobile.setOs(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 0:
                        return updated;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static Mobile findMobileById(String id, ArrayList<Mobile> mobiles) {
        for (Mobile mobile : mobiles) {
            if (mobile.getId().equals(id)) {
                return mobile;
            }
        }
        return null;
    }
}