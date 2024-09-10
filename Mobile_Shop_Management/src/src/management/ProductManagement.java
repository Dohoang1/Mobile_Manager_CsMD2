package management;

import entities.*;
import service.CurrencyFormatter;
import service.MobileService;
import service.ChargerService;
import service.MobileCaseService;
import service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {

    public static void showAllProducts(ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.println("===== All Products =====");

        showMobiles(mobiles);
        showChargers(chargers);
        showMobileCases(mobileCases);
    }

    private static void showMobiles(List<Mobile> mobiles) {
        System.out.println("\nMobile Phones:");
        if (mobiles.isEmpty()) {
            System.out.println("No mobile phones available.");
        } else {
            String format = "| %-6s | %-30s | %-15s | %-15s | %-15s | %-8s | %-10s |";
            System.out.println(String.format(format, "ID", "Name", "Brand", "Color", "Price", "Stock", "OS"));
            System.out.println("-".repeat(120));

            for (Mobile mobile : mobiles) {
                System.out.println(String.format(format,
                        mobile.getId(),
                        truncate(mobile.getName(), 30),
                        truncate(mobile.getBrand(), 15),
                        truncate(mobile.getColor(), 15),
                        CurrencyFormatter.formatToVND(mobile.getPrice()),
                        mobile.getStock(),
                        truncate(mobile.getOs(), 10)
                ));
            }
            System.out.println("-".repeat(120));
        }
    }

    private static void showChargers(List<Charger> chargers) {
        System.out.println("\nChargers:");
        if (chargers.isEmpty()) {
            System.out.println("No chargers available.");
        } else {
            String format = "| %-6s | %-30s | %-15s | %-15s | %-15s | %-8s | %-15s | %-15s |";
            System.out.println(String.format(format, "ID", "Name", "Brand", "Color", "Price", "Stock", "Cable Type", "Cable Length"));
            System.out.println("-".repeat(140));

            for (Charger charger : chargers) {
                System.out.println(String.format(format,
                        charger.getId(),
                        truncate(charger.getName(), 30),
                        truncate(charger.getBrand(), 15),
                        truncate(charger.getColor(), 15),
                        CurrencyFormatter.formatToVND(charger.getPrice()),
                        charger.getStock(),
                        truncate(charger.getCableType(), 15),
                        truncate(charger.getCableLength(), 15)
                ));
            }
            System.out.println("-".repeat(140));
        }
    }

    private static void showMobileCases(List<MobileCase> mobileCases) {
        System.out.println("\nMobile Cases:");
        if (mobileCases.isEmpty()) {
            System.out.println("No mobile cases available.");
        } else {
            String format = "| %-6s | %-30s | %-15s | %-15s | %-15s | %-8s | %-20s |";
            System.out.println(String.format(format, "ID", "Name", "Brand", "Color", "Price", "Stock", "Compatible With"));
            System.out.println("-".repeat(130));

            for (MobileCase mobileCase : mobileCases) {
                System.out.println(String.format(format,
                        mobileCase.getId(),
                        truncate(mobileCase.getName(), 30),
                        truncate(mobileCase.getBrand(), 15),
                        truncate(mobileCase.getColor(), 15),
                        CurrencyFormatter.formatToVND(mobileCase.getPrice()),
                        mobileCase.getStock(),
                        truncate(mobileCase.getUseFor(), 20)
                ));
            }
            System.out.println("-".repeat(130));
        }
    }

    private static String truncate(String str, int length) {
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }

    public static void editProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.print("Nhập ID của sản phẩm bạn muốn chỉnh sửa: ");
        String productId = input.nextLine().trim();

        Product product = findExistingProduct(productId, mobiles, chargers, mobileCases);

        if (product != null) {
            boolean updated = false;
            if (product instanceof Mobile) {
                updated = MobileService.editMobile((Mobile) product, input);
            } else if (product instanceof Charger) {
                updated = ChargerService.editCharger((Charger) product, input);
            } else if (product instanceof MobileCase) {
                updated = MobileCaseService.editMobileCase((MobileCase) product, input);
            }

            if (updated) {
                System.out.println("Sản phẩm đã được cập nhật thành công.");
                FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
            } else {
                System.out.println("Không có thay đổi nào được thực hiện cho sản phẩm.");
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID đã nhập.");
        }
    }

    public static void deleteProduct(Scanner input, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        System.out.print("Enter the ID of the product you want to delete: ");
        String productId = input.nextLine();

        Product product = findExistingProduct(productId, mobiles, chargers, mobileCases);

        if (product != null) {
            if (product instanceof Mobile) {
                mobiles.remove(product);
            } else if (product instanceof Charger) {
                chargers.remove(product);
            } else if (product instanceof MobileCase) {
                mobileCases.remove(product);
            }
            System.out.println("Product deleted successfully.");
            FileService.writeProductsToCSV(mobiles, chargers, mobileCases);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static Product findExistingProduct(String productId, ArrayList<Mobile> mobiles, ArrayList<Charger> chargers, ArrayList<MobileCase> mobileCases) {
        for (Mobile mobile : mobiles) {
            if (mobile.getId().equalsIgnoreCase(productId)) {
                return mobile;
            }
        }
        for (Charger charger : chargers) {
            if (charger.getId().equalsIgnoreCase(productId)) {
                return charger;
            }
        }
        for (MobileCase mobileCase : mobileCases) {
            if (mobileCase.getId().equalsIgnoreCase(productId)) {
                return mobileCase;
            }
        }
        return null;
    }
}