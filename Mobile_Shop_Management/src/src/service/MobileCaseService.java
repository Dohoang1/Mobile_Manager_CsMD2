package service;

import entities.MobileCase;
import factory.MobileCaseFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class MobileCaseService extends EditFieldProduct {
    public static void addMobileCase(ArrayList<MobileCase> mobileCases, Scanner input) {
        MobileCaseFactory factory = new MobileCaseFactory();
        MobileCase newMobileCase = (MobileCase) factory.createProduct(input);
        mobileCases.add(newMobileCase);
        System.out.println("Mobile Case added successfully.");
    }

    public static boolean editMobileCase(MobileCase mobileCase, Scanner input) {
        boolean updated = false;
        while (true) {
            System.out.println("\nEdit Mobile Case Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Brand");
            System.out.println("3. Edit ID");
            System.out.println("4. Edit Color");
            System.out.println("5. Edit Price");
            System.out.println("6. Edit Stock");
            System.out.println("7. Edit Status");
            System.out.println("8. Edit Use For");
            System.out.println("0. Finish Editing");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        mobileCase.setName(editStringField("name", mobileCase.getName(), input));
                        updated = true;
                        break;
                    case 2:
                        mobileCase.setBrand(editStringField("brand", mobileCase.getBrand(), input));
                        updated = true;
                        break;
                    case 3:
                        mobileCase.setId(editStringField("ID", mobileCase.getId(), input));
                        updated = true;
                        break;
                    case 4:
                        mobileCase.setColor(editStringField("color", mobileCase.getColor(), input));
                        updated = true;
                        break;
                    case 5:
                        mobileCase.setPrice(editIntField("price", mobileCase.getPrice(), input));
                        updated = true;
                        break;
                    case 6:
                        mobileCase.setStock(editIntField("stock", mobileCase.getStock(), input));
                        updated = true;
                        break;
                    case 7:
                        mobileCase.setStatus(editStringField("status", mobileCase.getStatus(), input));
                        updated = true;
                        break;
                    case 8:
                        mobileCase.setUseFor(editStringField("use for", mobileCase.getUseFor(), input));
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