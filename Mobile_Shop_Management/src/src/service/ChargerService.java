package service;

import entities.Charger;
import factory.ChargerFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class ChargerService extends EditFieldProduct {
    public static void addCharger(ArrayList<Charger> chargers, Scanner input) {
        ChargerFactory factory = new ChargerFactory();
        Charger newCharger = (Charger) factory.createProduct(input);
        chargers.add(newCharger);
        System.out.println("Charger added successfully.");
    }

    public static boolean editCharger(Charger charger, Scanner input) {
        boolean updated = false;
        while (true) {
            System.out.println("\nEdit Charger Menu:");
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Brand");
            System.out.println("3. Edit ID");
            System.out.println("4. Edit Color");
            System.out.println("5. Edit Price");
            System.out.println("6. Edit Stock");
            System.out.println("7. Edit Status");
            System.out.println("8. Edit Cable Type");
            System.out.println("9. Edit Cable Length");
            System.out.println("0. Finish Editing");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        charger.setName(editStringField("name", charger.getName(), input));
                        updated = true;
                        break;
                    case 2:
                        charger.setBrand(editStringField("brand", charger.getBrand(), input));
                        updated = true;
                        break;
                    case 3:
                        charger.setId(editStringField("ID", charger.getId(), input));
                        updated = true;
                        break;
                    case 4:
                        charger.setColor(editStringField("color", charger.getColor(), input));
                        updated = true;
                        break;
                    case 5:
                        charger.setPrice(editIntField("price", charger.getPrice(), input));
                        updated = true;
                        break;
                    case 6:
                        charger.setStock(editIntField("stock", charger.getStock(), input));
                        updated = true;
                        break;
                    case 7:
                        charger.setStatus(editStringField("status", charger.getStatus(), input));
                        updated = true;
                        break;
                    case 8:
                        charger.setCableType(editStringField("cable type", charger.getCableType(), input));
                        updated = true;
                        break;
                    case 9:
                        charger.setCableLength(editStringField("cable length", charger.getCableLength(), input));
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