package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;

import java.util.List;

/**
 * This is the class that implements strategy design pattern that implements DiscountStrategy.
 * It applies discount based on the itemID.
 */
public class ItemDiscountStrategy implements DiscountStrategy {
    private List<Integer> itemIDs;

    public ItemDiscountStrategy(List<Integer> itemIDs) {
        this.itemIDs = itemIDs;
    }
    @Override
    public double calculateDiscount(double totalPrice, int customerID) {
        double discount = 0.0;
        for (int id : itemIDs) {
            discount += totalPrice * DiscountDatabase.getInstance().getItemDiscount(id);
        }
        return discount;
    }
}
