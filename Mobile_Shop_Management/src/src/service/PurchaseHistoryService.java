package service;

import controller.Customer;
import entities.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Collections;

public class PurchaseHistoryService {
    private static final String PURCHASE_HISTORY_FILE = "purchase_history.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void addPurchaseToHistory(Customer customer, List<Product> products, double totalAmount) {
        LocalDateTime purchaseDate = LocalDateTime.now();
        StringBuilder purchaseRecord = new StringBuilder();
        purchaseRecord.append(String.format("%s,%s,", customer.getUsername(), purchaseDate.format(DATE_FORMATTER)));

        Map<Product, Integer> productCounts = new HashMap<>();
        for (Product product : products) {
            productCounts.put(product, productCounts.getOrDefault(product, 0) + 1);
        }

        for (Map.Entry<Product, Integer> entry : productCounts.entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            purchaseRecord.append(String.format("%s:%d:%d;", product.getName(), product.getPrice(), count));
        }
        purchaseRecord.append(String.format(",%.2f", totalAmount));

        try (FileWriter fw = new FileWriter(PURCHASE_HISTORY_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(purchaseRecord);
        } catch (IOException e) {
            System.out.println("Error writing to purchase history file: " + e.getMessage());
        }
    }

    public static Map<String, List<String>> getCustomerPurchaseHistory(String username) {
        Map<String, List<String>> history = new LinkedHashMap<>();
        double totalPurchased = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PURCHASE_HISTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username)) {
                    String date = parts[1];
                    StringBuilder entry = new StringBuilder();
                    entry.append(String.format("Date: %s - Products: ", date));

                    double total = 0;
                    if (parts.length >= 3) {
                        String[] products = parts[2].split(";");
                        for (String product : products) {
                            String[] productInfo = product.split(":");
                            if (productInfo.length >= 3) {
                                entry.append(String.format("%s - Price: %s - Amount: %s; ",
                                        productInfo[0],
                                        CurrencyFormatter.formatToVND(Double.parseDouble(productInfo[1])),
                                        productInfo[2]));
                            }
                        }
                    }

                    if (parts.length >= 4) {
                        total = Double.parseDouble(parts[3]);
                    }

                    totalPurchased += total;
                    entry.append(String.format("Total: %s", CurrencyFormatter.formatToVND(total)));

                    history.computeIfAbsent(username, k -> new ArrayList<>()).add(entry.toString());
                }
            }
            history.put("Total", Collections.singletonList(CurrencyFormatter.formatToVND(totalPurchased)));
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading purchase history file: " + e.getMessage());
        }
        return history;
    }

    public static Map<String, Map<String, List<String>>> getAllCustomersPurchaseHistory() {
        Map<String, Map<String, List<String>>> allHistory = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PURCHASE_HISTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                String username = parts[0];
                allHistory.putIfAbsent(username, getCustomerPurchaseHistory(username));
            }
        } catch (IOException e) {
            System.out.println("Error reading purchase history file: " + e.getMessage());
        }
        return allHistory;
    }

    public static void viewCustomerPurchaseHistory(String username) {
        Map<String, List<String>> history = getCustomerPurchaseHistory(username);
        if (history.isEmpty() || !history.containsKey(username)) {
            System.out.println("No purchase history found for " + username);
        } else {
            System.out.println("===== Purchase History =====");
            System.out.println("Name: " + username);
            List<String> entries = history.get(username);
            for (String entry : entries) {
                System.out.println(entry);
            }
            if (history.containsKey("Total")) {
                System.out.println("Total purchased: " + history.get("Total").get(0));
            }
        }
    }

    public static void viewAllCustomersPurchaseHistory() {
        Map<String, Map<String, List<String>>> allHistory = getAllCustomersPurchaseHistory();
        if (allHistory.isEmpty()) {
            System.out.println("No purchase history found for any customer.");
        } else {
            System.out.println("===== All Customers' Purchase History =====");
            for (Map.Entry<String, Map<String, List<String>>> customerEntry : allHistory.entrySet()) {
                String username = customerEntry.getKey();
                Map<String, List<String>> history = customerEntry.getValue();
                System.out.println("Name: " + username);
                List<String> entries = history.get(username);
                for (String entry : entries) {
                    System.out.println(entry);
                }
                System.out.println("Total purchased: " + history.get("Total").get(0));
                System.out.println();
            }
        }
    }
}