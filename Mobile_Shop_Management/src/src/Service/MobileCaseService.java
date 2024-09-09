package Service;

import Entities.MobileCase;
import Factory.MobileCaseFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class MobileCaseService extends ProductService {
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
                        if (editStringField("name", mobileCase.getName(), input)) {
                            mobileCase.setName(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 2:
                        if (editStringField("brand", mobileCase.getBrand(), input)) {
                            mobileCase.setBrand(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 3:
                        if (editStringField("ID", mobileCase.getId(), input)) {
                            mobileCase.setId(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 4:
                        if (editStringField("color", mobileCase.getColor(), input)) {
                            mobileCase.setColor(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 5:
                        if (editIntField("price", mobileCase.getPrice(), input)) {
                            mobileCase.setPrice(Integer.parseInt(input.nextLine().trim()));
                            updated = true;
                        }
                        break;
                    case 6:
                        if (editIntField("stock", mobileCase.getStock(), input)) {
                            mobileCase.setStock(Integer.parseInt(input.nextLine().trim()));
                            updated = true;
                        }
                        break;
                    case 7:
                        if (editStringField("status", mobileCase.getStatus(), input)) {
                            mobileCase.setStatus(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 8:
                        if (editStringField("use for", mobileCase.getUseFor(), input)) {
                            mobileCase.setUseFor(input.nextLine().trim());
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

    public static MobileCase findMobileCaseById(String id, ArrayList<MobileCase> mobileCases) {
        for (MobileCase mobileCase : mobileCases) {
            if (mobileCase.getId().equals(id)) {
                return mobileCase;
            }
        }
        return null;
    }
}