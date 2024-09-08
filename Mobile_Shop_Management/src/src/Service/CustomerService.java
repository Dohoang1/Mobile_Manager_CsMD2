package Service;

import Controller.Customer;
import java.util.ArrayList;

public class CustomerService {

    public static void addCustomer(ArrayList<Customer> customers, String username, String password) {
        customers.add(new Customer(username, password));
        FileService.writeCustomersToCSV(customers);
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