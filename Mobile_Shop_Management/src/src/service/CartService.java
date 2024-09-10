package service;

import controller.Customer;
import entities.Product;
import entities.Mobile;
import entities.Charger;
import entities.MobileCase;
import management.ProductManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CartService {
    public static void addProductToCart(ArrayList<Product> cart,
                                        ArrayList<Mobile> mobiles,
                                        ArrayList<Charger> chargers,
                                        ArrayList<MobileCase> mobileCases,
                                        Scanner scanner)
    {
        System.out.print("Enter product ID to add to cart: ");
        String productId = scanner.nextLine().trim();

        Product product = ProductManagement.findExistingProduct(productId, mobiles, chargers, mobileCases);

        if (product != null) {
            try {
                System.out.print("Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine().trim());

                if (quantity <= 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0.");
                }

                if (quantity > product.getStock()) {
                    throw new IllegalArgumentException("Not enough stock. Available: " + product.getStock());
                }

                for (int i = 0; i < quantity; i++) {
                    cart.add(product);
                }
                System.out.println(quantity + " " + product.getName() + "(s) added to cart.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void showCart(ArrayList<Product> cart) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            Map<Product, Integer> productCounts = new HashMap<>();
            double cartTotal = 0;

            for (Product product : cart) {
                productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
                cartTotal += product.getPrice();
            }

            for (Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                Product product = entry.getKey();
                int amount = entry.getValue();
                double totalForProduct = product.getPrice() * amount;

                System.out.printf("Products: %s - Price: %s - Amount: %d; Total: %s%n",
                        product.getName(),
                        CurrencyFormatter.formatToVND(product.getPrice()),
                        amount,
                        CurrencyFormatter.formatToVND(totalForProduct));
            }

            System.out.println("Cart total: " + CurrencyFormatter.formatToVND(cartTotal));
        }
    }

    public static void checkout(ArrayList<Product> cart, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases, Customer customer, Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Please add products to your cart before checking out.");
            return;
        }

        System.out.println("===== Checkout =====");
        Map<Product, Integer> productCounts = new HashMap<>();
        double cartTotal = 0;

        for (Product product : cart) {
            productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
            cartTotal += product.getPrice();
        }

        for (Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
            Product product = entry.getKey();
            int amount = entry.getValue();
            double totalForProduct = product.getPrice() * amount;

            System.out.printf("Products: %s - Price: %s - Amount: %d; Total: %s%n",
                    product.getName(),
                    CurrencyFormatter.formatToVND(product.getPrice()),
                    amount,
                    CurrencyFormatter.formatToVND(totalForProduct));
        }

        System.out.println("Cart total: " + CurrencyFormatter.formatToVND(cartTotal));

        System.out.print("Proceed to checkout? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            for (Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                Product cartProduct = entry.getKey();
                int amount = entry.getValue();
                Product stockProduct = ProductManagement.findExistingProduct(cartProduct.getId(), mobiles, chargers, mobileCases);
                if (stockProduct != null) {
                    stockProduct.setStock(stockProduct.getStock() - amount);
                }
            }
            PurchaseHistoryService.addPurchaseToHistory(customer, new ArrayList<>(productCounts.keySet()), cartTotal);
            System.out.println("Checkout complete. Thank you for your purchase!");
            cart.clear();
            FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
        } else {
            System.out.println("Checkout canceled.");
        }
    }
}