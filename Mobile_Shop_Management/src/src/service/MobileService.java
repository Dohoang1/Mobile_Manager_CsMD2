package service;

import entities.Mobile;
import factory.MobileFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class MobileService extends EditFieldProduct {
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
                        mobile.setName(editStringField("name", mobile.getName(), input));
                        updated = true;
                        break;
                    case 2:
                        mobile.setBrand(editStringField("brand", mobile.getBrand(), input));
                        updated = true;
                        break;
                    case 3:
                        mobile.setId(editStringField("ID", mobile.getId(), input));
                        updated = true;
                        break;
                    case 4:
                        mobile.setColor(editStringField("color", mobile.getColor(), input));
                        updated = true;
                        break;
                    case 5:
                        mobile.setPrice(editIntField("price", mobile.getPrice(), input));
                        updated = true;
                        break;
                    case 6:
                        mobile.setStock(editIntField("stock", mobile.getStock(), input));
                        updated = true;
                        break;
                    case 7:
                        mobile.setStatus(editStringField("status", mobile.getStatus(), input));
                        updated = true;
                        break;
                    case 8:
                        mobile.setOs(editStringField("OS", mobile.getOs(), input));
                        updated = true;
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
}