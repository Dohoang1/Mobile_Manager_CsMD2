package service;

import java.text.DecimalFormat;

public class CurrencyFormatter {


        private static final DecimalFormat VND_FORMATTER = new DecimalFormat("###,###,### VNĐ");

        public static String formatToVND(double amount) {
            return VND_FORMATTER.format(amount);
        }


}
