package Management;

import Entities.*;
import Service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductManagement {

    public static void showAllProducts(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.println("===== All Products =====");
        System.out.println("Mobiles:");
        for (Mobile mobile : mobiles) {
            System.out.println(mobile);
        }
        System.out.println("\nChargers:");
        for (Charger charger : chargers) {
            System.out.println(charger);
        }
        System.out.println("\nMobile Cases:");
        for (MobileCase mobileCase : mobileCases) {
            System.out.println(mobileCase);
        }
    }

    public static void editProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.println("Edit Product");
        Product productToEdit = findExistingProduct(input, mobiles, chargers, mobileCases);

        if (productToEdit != null) {
            boolean updated = false;
            if (productToEdit instanceof Mobile) {
                updated = MobileService.editMobile((Mobile) productToEdit, input);
            } else if (productToEdit instanceof Charger) {
                updated = ChargerService.editCharger((Charger) productToEdit, input);
            } else if (productToEdit instanceof MobileCase) {
                updated = MobileCaseService.editMobileCase((MobileCase) productToEdit, input);
            }

            if (updated) {
                FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
                System.out.println("Product edited successfully.");
            } else {
                System.out.println("No changes were made to the product.");
            }
        } else {
            System.out.println("Edit operation cancelled.");
        }
    }

    public static void deleteProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.println("Delete Product");
        Product productToDelete = findExistingProduct(input, mobiles, chargers, mobileCases);

        if (productToDelete != null) {
            System.out.print("Are you sure you want to delete this product? (yes/no): ");
            String confirmation = input.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes")) {
                boolean deleted = false;
                if (productToDelete instanceof Mobile) {
                    deleted = mobiles.remove(productToDelete);
                } else if (productToDelete instanceof Charger) {
                    deleted = chargers.remove(productToDelete);
                } else if (productToDelete instanceof MobileCase) {
                    deleted = mobileCases.remove(productToDelete);
                }

                if (deleted) {
                    FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
                    System.out.println("Product deleted successfully.");
                } else {
                    System.out.println("Failed to delete the product. Please try again.");
                }
            } else {
                System.out.println("Delete operation cancelled.");
            }
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    public static Product findExistingProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        while (true) {
            System.out.print("Enter product name or id to search (or 'exit' to cancel): ");
            String searchQuery = input.nextLine().trim().toLowerCase();

            if (searchQuery.equals("exit")) {
                return null;
            }

            Product foundProduct = searchInList(searchQuery, mobiles);
            if (foundProduct == null) {
                foundProduct = searchInList(searchQuery, chargers);
            }
            if (foundProduct == null) {
                foundProduct = searchInList(searchQuery, mobileCases);
            }

            if (foundProduct != null) {
                System.out.println("Found product: " + foundProduct.getName() + " (ID: " + foundProduct.getId() + ")");
                return foundProduct;
            } else {
                System.out.println("Product not found. Please try again.");
            }
        }
    }

    private static <T extends Product> T searchInList(String searchQuery, ArrayList<T> products) {
        for (T product : products) {
            if (product.getName().toLowerCase().contains(searchQuery) ||
                    product.getId().toLowerCase().equals(searchQuery)) {
                return product;
            }
        }
        return null;
    }
}