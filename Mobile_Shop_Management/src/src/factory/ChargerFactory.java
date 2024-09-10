package factory;

import entities.Charger;
import entities.Product;

import java.util.Scanner;

public class ChargerFactory extends ProductFactory {
    @Override
    public Product createProduct(Scanner input) {
        System.out.println("Add new Charger");
        return new Charger.Builder()
                .setName(promptForInput(input, "Enter name:"))
                .setBrand(promptForInput(input, "Enter brand:"))
                .setId(promptForInput(input, "Enter ID:"))
                .setColor(promptForInput(input, "Enter color:"))
                .setPrice(Integer.parseInt(promptForInput(input, "Enter price:")))
                .setStock(Integer.parseInt(promptForInput(input, "Enter stock:")))
                .setStatus(promptForInput(input, "Enter status:"))
                .setCableType(promptForInput(input, "Enter cable type:"))
                .setCableLength(promptForInput(input, "Enter cable length:"))
                .build();
    }
}