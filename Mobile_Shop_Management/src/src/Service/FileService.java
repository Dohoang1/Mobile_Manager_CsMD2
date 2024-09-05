package Service;

import Entities.Mobile;
import Entities.Charger;
import Entities.MobileCase;
import java.io.*;
import java.util.ArrayList;

public class FileService {
    private static final String CSV_FILE_PATH = "Mobile_Shop_Management\\products.csv";

    public static void readProductsFromCSV(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        mobiles.clear();
        chargers.clear();
        mobileCases.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String type = values[0];
                switch (type) {
                    case "Mobile":
                        mobiles.add(new Mobile.Builder()
                                .setName(values[1])
                                .setBrand(values[2])
                                .setId(values[3])
                                .setColor(values[4])
                                .setPrice(Integer.parseInt(values[5]))
                                .setStock(Integer.parseInt(values[6]))
                                .setStatus(values[7])
                                .setOs(values[8])
                                .build());
                        break;
                    case "Charger":
                        chargers.add(new Charger.Builder()
                                .setName(values[1])
                                .setBrand(values[2])
                                .setId(values[3])
                                .setColor(values[4])
                                .setPrice(Integer.parseInt(values[5]))
                                .setStock(Integer.parseInt(values[6]))
                                .setStatus(values[7])
                                .setCableType(values[8])
                                .setCableLength(values[9])
                                .build());
                        break;
                    case "MobileCase":
                        mobileCases.add(new MobileCase.Builder()
                                .setName(values[1])
                                .setBrand(values[2])
                                .setId(values[3])
                                .setColor(values[4])
                                .setPrice(Integer.parseInt(values[5]))
                                .setStock(Integer.parseInt(values[6]))
                                .setStatus(values[7])
                                .setUseFor(values[8])
                                .build());
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeProductsToCSV(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (Mobile mobile : mobiles) {
                bw.write("Mobile," + mobile.toCSV());
                bw.newLine();
            }
            for (Charger charger : chargers) {
                bw.write("Charger," + charger.toCSV());
                bw.newLine();
            }
            for (MobileCase mobileCase : mobileCases) {
                bw.write("MobileCase," + mobileCase.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}