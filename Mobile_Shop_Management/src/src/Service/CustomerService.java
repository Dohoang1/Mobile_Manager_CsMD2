package Service;

import Controller.Customer;
import java.io.*;
import java.util.ArrayList;

public class CustomerService {
    private static final String CUSTOMER_CSV_FILE_PATH = "Mobile_Shop_Management\\customers.csv";

    public static void readCustomersFromCSV(ArrayList<Customer> customers) {
        customers.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                customers.add(new Customer(values[0], values[1]));
            }
        } catch (IOException e) {
            System.out.println("No existing customer data found. Starting with an empty customer list.");
        }
    }

    public static void writeCustomersToCSV(ArrayList<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_CSV_FILE_PATH))) {
            for (Customer customer : customers) {
                bw.write(customer.getUsername() + "," + customer.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(ArrayList<Customer> customers, String username, String password) {
        customers.add(new Customer(username, password));
        writeCustomersToCSV(customers);
    }

    public static Customer findCustomerByUsername(ArrayList<Customer> customers, String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }
}