package Service;

import Entities.Charger;
import Factory.ChargerFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class ChargerService extends ProductService {
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
                        if (editStringField("name", charger.getName(), input)) {
                            charger.setName(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 2:
                        if (editStringField("brand", charger.getBrand(), input)) {
                            charger.setBrand(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 3:
                        if (editStringField("ID", charger.getId(), input)) {
                            charger.setId(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 4:
                        if (editStringField("color", charger.getColor(), input)) {
                            charger.setColor(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 5:
                        if (editIntField("price", charger.getPrice(), input)) {
                            charger.setPrice(Integer.parseInt(input.nextLine().trim()));
                            updated = true;
                        }
                        break;
                    case 6:
                        if (editIntField("stock", charger.getStock(), input)) {
                            charger.setStock(Integer.parseInt(input.nextLine().trim()));
                            updated = true;
                        }
                        break;
                    case 7:
                        if (editStringField("status", charger.getStatus(), input)) {
                            charger.setStatus(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 8:
                        if (editStringField("cable type", charger.getCableType(), input)) {
                            charger.setCableType(input.nextLine().trim());
                            updated = true;
                        }
                        break;
                    case 9:
                        if (editStringField("cable length", charger.getCableLength(), input)) {
                            charger.setCableLength(input.nextLine().trim());
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

    public static Charger findChargerById(String id, ArrayList<Charger> chargers) {
        for (Charger charger : chargers) {
            if (charger.getId().equals(id)) {
                return charger;
            }
        }
        return null;
    }
}