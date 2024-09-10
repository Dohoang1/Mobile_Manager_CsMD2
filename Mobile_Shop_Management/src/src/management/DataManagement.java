package management;

import controller.Customer;
import entities.Charger;
import entities.Mobile;
import entities.MobileCase;

import java.util.ArrayList;

public class DataManagement {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Mobile> mobiles = new ArrayList<>();
    private static ArrayList<Charger> chargers = new ArrayList<>();
    private static ArrayList<MobileCase> mobileCases = new ArrayList<>();

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Mobile> getMobiles() {
        return mobiles;
    }

    public static ArrayList<Charger> getChargers() {
        return chargers;
    }

    public static ArrayList<MobileCase> getMobileCases() {
        return mobileCases;
    }
}