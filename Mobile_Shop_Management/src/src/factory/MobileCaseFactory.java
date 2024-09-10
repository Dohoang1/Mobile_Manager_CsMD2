package factory;

import entities.MobileCase;
import entities.Product;

import java.util.Scanner;

public class MobileCaseFactory extends ProductFactory {
    @Override
    public Product createProduct(Scanner input) {
        System.out.println("Add new MobileCase");
        return new MobileCase.Builder()
                .setName(promptForInput(input, "Enter name:"))
                .setBrand(promptForInput(input, "Enter brand:"))
                .setId(promptForInput(input, "Enter ID:"))
                .setColor(promptForInput(input, "Enter color:"))
                .setPrice(Integer.parseInt(promptForInput(input, "Enter price:")))
                .setStock(Integer.parseInt(promptForInput(input, "Enter stock:")))
                .setStatus(promptForInput(input, "Enter status:"))
                .setUseFor(promptForInput(input, "Enter mobile suit:"))
                .build();
    }
}