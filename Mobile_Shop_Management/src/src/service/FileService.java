package service;

import controller.Customer;
import entities.Mobile;
import entities.Charger;
import entities.MobileCase;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileService {
    private static final String CSV_FILE_PATH = "products.csv";
    private static final String CUSTOMERS_CSV_FILE_PATH = "customers.csv";

    public static void readProductsFromCSV(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        mobiles.clear();
        chargers.clear();
        mobileCases.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 8) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String type = values[0];
                switch (type) {
                    case "Mobile":
                        if (values.length >= 9) {
                            mobiles.add(new Mobile.Builder()
                                    .setName(values[1])
                                    .setBrand(values[2])
                                    .setId(values[3])
                                    .setColor(values[4])
                                    .setPrice(Integer.parseInt(values[5]))
                                    .setStock(Integer.parseInt(values[6]))
                                    .setStatus(values[7])
                                    .setOs(values[8])
                                    .build());
                        }
                        break;
                    case "Charger":
                        if (values.length >= 10) {
                            chargers.add(new Charger.Builder()
                                    .setName(values[1])
                                    .setBrand(values[2])
                                    .setId(values[3])
                                    .setColor(values[4])
                                    .setPrice(Integer.parseInt(values[5]))
                                    .setStock(Integer.parseInt(values[6]))
                                    .setStatus(values[7])
                                    .setCableType(values[8])
                                    .setCableLength(values[9])
                                    .build());
                        }
                        break;
                    case "MobileCase":
                        if (values.length >= 9) {
                            mobileCases.add(new MobileCase.Builder()
                                    .setName(values[1])
                                    .setBrand(values[2])
                                    .setId(values[3])
                                    .setColor(values[4])
                                    .setPrice(Integer.parseInt(values[5]))
                                    .setStock(Integer.parseInt(values[6]))
                                    .setStatus(values[7])
                                    .setUseFor(values[8])
                                    .build());
                        }
                        break;
                    default:
                        System.out.println("Unknown product type: " + type);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing products data found. Starting with an empty product list.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numeric value: " + e.getMessage());
        }
    }

    public static void writeProductsToCSV(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Mobile mobile : mobiles) {
                bw.write("Mobile," + mobile.toCSV());
                bw.newLine();
            }
            for (Charger charger : chargers) {
                bw.write("Charger," + charger.toCSV());
                bw.newLine();
            }
            for (MobileCase mobileCase : mobileCases) {
                bw.write("MobileCase," + mobileCase.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCustomersFromCSV(ArrayList<Customer> customers) {
        customers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMERS_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                customers.add(new Customer(values[0], values[1]));
            }
        } catch (IOException e) {
            System.out.println("No existing customers data found. Starting with an empty customer list.");
        }
    }

    public static void writeCustomersToCSV(ArrayList<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMERS_CSV_FILE_PATH))) {
            for (Customer customer : customers) {
                bw.write(customer.getUsername() + "," + customer.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}