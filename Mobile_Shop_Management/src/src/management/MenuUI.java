package management;

import controller.Admin;
import controller.Customer;
import entities.Charger;
import entities.Mobile;
import entities.MobileCase;
import entities.Product;
import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuUI {
    private Scanner input;
    private ArrayList<Customer> customers;
    private ArrayList<Mobile> mobiles;
    private ArrayList<Charger> chargers;
    private ArrayList<MobileCase> mobileCases;

    public MenuUI(Scanner input, ArrayList<Customer> customers, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        this.input = input;
        this.customers = customers;
        this.mobiles = mobiles;
        this.chargers = chargers;
        this.mobileCases = mobileCases;
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("Welcome to DHStore");
            System.out.println("1. Login");
            System.out.println("2. Register as customer");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Exiting program... ");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void loginMenu() {
        System.out.println("Login Menu");
        System.out.println("1. Login as customer");
        System.out.println("2. login as admin");
        System.out.println("0. Return");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();
        switch (choice) {
            case 1:
                customerLogin();
                break;
            case 2:
                adminLogin();
                break;
            case 0:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void register() {
        System.out.println("Enter username: ");
        String username = input.nextLine();
        if (CustomerService.isUsernameExists(customers, username)) {
            System.out.println("Username already exists. Please choose another one.");
            return;
        }
        System.out.println("Enter password: ");
        String password = input.nextLine();

        CustomerService.addCustomer(customers, username, password);
        System.out.println("Register successful");
    }

    private void customerLogin() {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        Customer customer = CustomerService.findCustomerByUsername(customers, username);
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome " + username + "!");
            customerMenu(customer);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void adminLogin() {
        Admin admin = Admin.getInstance();
        System.out.print("Enter admin username: ");
        String username = input.nextLine();
        System.out.print("Enter admin password: ");
        String password = input.nextLine();
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("Admin login successful. Welcome Admin!");
            adminMenu();
        } else {
            System.out.println("Invalid admin user. Please try again.");
        }
    }

    private void customerMenu(Customer customer) {
        ArrayList<Product> cart = new ArrayList<>();
        int choice;
        do {
            System.out.println("===== Customer Menu =====");
            System.out.println("1. Show All Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Show Cart");
            System.out.println("4. Checkout");
            System.out.println("5. View Purchase History");
            System.out.println("0. Logout");
            System.out.print("Please choose an option: ");
            choice = getIntInput();
            switch (choice) {
                case 1:
                    ProductManagement.showAllProducts(mobiles, chargers, mobileCases);
                    break;
                case 2:
                    CartService.addProductToCart(cart, mobiles, chargers, mobileCases, input);
                    break;
                case 3:
                    CartService.showCart(cart);
                    break;
                case 4:
                    CartService.checkout(cart, mobiles, chargers, mobileCases, customer, input);
                    break;
                case 5:
                    PurchaseHistoryService.viewCustomerPurchaseHistory(customer.getUsername());
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void adminMenu() {
        int choice;
        do {
            System.out.println("\n Admin Menu");
            System.out.println("1. Add new Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Edit Product");
            System.out.println("4. Show all Products");
            System.out.println("5. View All Customers' Purchase History");
            System.out.println("0. Exit");
            choice = getIntInput();
            switch (choice) {
                case 1:
                    addNewProductMenu();
                    break;
                case 2:
                    ProductManagement.deleteProduct(input, mobiles, chargers, mobileCases);
                    break;
                case 3:
                    ProductManagement.editProduct(input, mobiles, chargers, mobileCases);
                    break;
                case 4:
                    ProductManagement.showAllProducts(mobiles, chargers, mobileCases);
                    break;
                case 5:
                    PurchaseHistoryService.viewAllCustomersPurchaseHistory();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }


    void addNewProductMenu() {
        System.out.println("Add new Product");
        System.out.println("1. Add new Mobile");
        System.out.println("2. Add new Charger");
        System.out.println("3. Add new MobileCase");
        System.out.println("0. Return");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                MobileService.addMobile(mobiles, input);
                break;
            case 2:
                ChargerService.addCharger(chargers, input);
                break;
            case 3:
                MobileCaseService.addMobileCase(mobileCases, input);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
    }

    private int getIntInput() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }
}