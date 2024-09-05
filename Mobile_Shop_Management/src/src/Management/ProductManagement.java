package Management;

import Entities.Mobile;
import Entities.Charger;
import Entities.MobileCase;
import Entities.Product;
import Service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagement {
    public static void showAllProducts(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        MenuUI menuUI = new MenuUI(new Scanner(System.in), null, mobiles, chargers, mobileCases);
        menuUI.showAllProducts();
    }

    public static void addNewProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        MenuUI menuUI = new MenuUI(input, null, mobiles, chargers, mobileCases);
        menuUI.addNewProductMenu();
    }

    public static void deleteProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.println("Delete Product");
        Product productToDelete = findExistingProduct(input, new ArrayList<>(mobiles));
        if (productToDelete == null) {
            productToDelete = findExistingProduct(input, new ArrayList<>(chargers));
        }
        if (productToDelete == null) {
            productToDelete = findExistingProduct(input, new ArrayList<>(mobileCases));
        }

        if (productToDelete != null) {
            if (productToDelete instanceof Mobile) {
                mobiles.remove(productToDelete);
            } else if (productToDelete instanceof Charger) {
                chargers.remove(productToDelete);
            } else if (productToDelete instanceof MobileCase) {
                mobileCases.remove(productToDelete);
            }
            System.out.println("Product deleted successfully.");
            FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
        }
    }

    public static void editProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.println("Edit Product");
        Product productToEdit = findExistingProduct(input, new ArrayList<>(mobiles));
        if (productToEdit == null) {
            productToEdit = findExistingProduct(input, new ArrayList<>(chargers));
        }
        if (productToEdit == null) {
            productToEdit = findExistingProduct(input, new ArrayList<>(mobileCases));
        }

        if (productToEdit != null) {
            if (productToEdit instanceof Mobile) {
                MobileService.editMobile((Mobile) productToEdit, input);
            } else if (productToEdit instanceof Charger) {
                ChargerService.editCharger((Charger) productToEdit, input);
            } else if (productToEdit instanceof MobileCase) {
                MobileCaseService.editMobileCase((MobileCase) productToEdit, input);
            }
            FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
        }
    }

    private static Product findExistingProduct(Scanner input, ArrayList<? extends Product> products) {
        System.out.println("Enter product name or id to search: ");
        String searchQuery = input.nextLine();

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(searchQuery) || product.getId().equalsIgnoreCase(searchQuery)) {
                return product;
            }
        }
        System.out.println("Product not found.");
        return null;
    }
}