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

    public static void editCharger(Charger charger, Scanner input) {
        System.out.println("Editing Charger: " + charger.getName());
        editCommonFields(charger, input);
        System.out.println("Enter new cable type: ");
        charger.setCableType(input.nextLine());
        System.out.println("Enter new cable length: ");
        charger.setCableLength(input.nextLine());
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