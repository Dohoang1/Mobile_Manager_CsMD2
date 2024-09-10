package management;

import controller.Customer;
import entities.Charger;
import entities.Mobile;
import entities.MobileCase;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuManagement {
    private MenuUI menuUI;

    public MenuManagement(ArrayList<Customer> customers,
                          ArrayList<Mobile> mobiles,
                          ArrayList<Charger> chargers,
                          ArrayList<MobileCase> mobileCases) {
        Scanner input = new Scanner(System.in);
        this.menuUI = new MenuUI(input, customers, mobiles, chargers, mobileCases);
    }

    public void mainMenu() {
        menuUI.mainMenu();
    }
}