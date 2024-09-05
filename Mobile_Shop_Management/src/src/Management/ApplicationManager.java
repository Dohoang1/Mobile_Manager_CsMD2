package Management;

import Service.CustomerService;
import Service.FileService;

public class ApplicationManager {
    private static DataManagement dataManagement;
    private static MenuManagement menuManagement;

    public static void run() {
        initialize();
        initializeData();
        runMainMenu();
        saveData();
    }

    private static void initialize() {
        dataManagement = new DataManagement();
        menuManagement = new MenuManagement(
                dataManagement.getCustomers(),
                dataManagement.getMobiles(),
                dataManagement.getChargers(),
                dataManagement.getMobileCases()
        );
    }

    private static void initializeData() {
        FileService.readProductsFromCSV(
                dataManagement.getMobiles(),
                dataManagement.getChargers(),
                dataManagement.getMobileCases()
        );
        CustomerService.readCustomersFromCSV(dataManagement.getCustomers());
    }

    private static void runMainMenu() {
        menuManagement.mainMenu();
    }

    private static void saveData() {
        FileService.writeProductsToCSV(
                dataManagement.getMobiles(),
                dataManagement.getChargers(),
                dataManagement.getMobileCases()
        );
        CustomerService.writeCustomersToCSV(dataManagement.getCustomers());
    }
}