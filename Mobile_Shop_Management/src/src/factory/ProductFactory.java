package factory;

import entities.Product;

import java.util.Scanner;

public abstract class ProductFactory {

    public abstract Product createProduct(Scanner input);

    protected String promptForInput(Scanner input, String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }
}