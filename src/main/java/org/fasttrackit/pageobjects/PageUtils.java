package org.fasttrackit.pageobjects;

public class PageUtils {
    public static double convertPrice(String priceString) {
        String[] newPriceString = priceString.split(" ");
        String intPriceString = newPriceString[0];
        intPriceString = intPriceString.replace(",", ".");
        double priceValue = Double.parseDouble(intPriceString);
        return priceValue;
    }
}
