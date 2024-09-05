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

    public static void editMobile(Mobile mobile, Scanner input) {
        System.out.println("Editing Mobile: " + mobile.getName());
        editCommonFields(mobile, input);
        System.out.println("Enter new OS: ");
        mobile.setOs(input.nextLine());
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