package management;
import service.FileService;

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
        FileService.readCustomersFromCSV(dataManagement.getCustomers());
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
        FileService.writeCustomersToCSV(dataManagement.getCustomers());
    }
}