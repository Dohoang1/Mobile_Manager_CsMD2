package Service;

import Controller.Customer;
import Entities.Product;
import Entities.Mobile;
import Entities.Charger;
import Entities.MobileCase;
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
        System.out.print("Enter product type (1: Mobile, 2: Charger, 3: Mobile Case): ");
        int productType = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter product ID to add to cart: ");
        String productId = scanner.nextLine();

        Product product = null;
        switch (productType) {
            case 1:
                product = MobileService.findMobileById(productId, mobiles);
                break;
            case 2:
                product = ChargerService.findChargerById(productId, chargers);
                break;
            case 3:
                product = MobileCaseService.findMobileCaseById(productId, mobileCases);
                break;
            default:
                System.out.println("Invalid product type.");
                return;
        }

        if (product != null) {
            try {
                System.out.print("Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());

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

    public static void checkout(ArrayList<Product> cart, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases, Customer customer) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Please add products to your cart before checking out.");
        } else {
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

            Scanner scanner = new Scanner(System.in);
            System.out.print("Proceed to checkout? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                for (Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
                    Product cartProduct = entry.getKey();
                    int amount = entry.getValue();
                    Product stockProduct = findProductInStock(cartProduct, mobiles, chargers, mobileCases);
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

    private static Product findProductInStock(Product product, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        if (product instanceof Mobile) {
            return MobileService.findMobileById(product.getId(), mobiles);
        } else if (product instanceof Charger) {
            return ChargerService.findChargerById(product.getId(), chargers);
        } else if (product instanceof MobileCase) {
            return MobileCaseService.findMobileCaseById(product.getId(), mobileCases);
        }
        return null;
    }
}