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

    public static void editMobileCase(MobileCase mobileCase, Scanner input) {
        System.out.println("Editing Mobile Case: " + mobileCase.getName());
        editCommonFields(mobileCase, input);
        System.out.println("Enter new mobile suit: ");
        mobileCase.setUseFor(input.nextLine());
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