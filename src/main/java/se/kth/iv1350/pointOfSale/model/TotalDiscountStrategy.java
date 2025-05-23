package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;

/**
 * This is the class that implements strategy design pattern that implements DiscountStrategy.
 * It applies discount based on the total price of the sale.
 */
public class TotalDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double totalPrice, int customerID) {
        double rate = DiscountDatabase.getInstance().getTotalDiscount(totalPrice);
        return totalPrice * rate;
    }
}