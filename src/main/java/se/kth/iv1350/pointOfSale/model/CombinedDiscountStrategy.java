package se.kth.iv1350.pointOfSale.model;

import java.util.List;

public class CombinedDiscountStrategy implements DiscountStrategy {
    private List<DiscountStrategy> discountStrategiesList;

    public CombinedDiscountStrategy(List<DiscountStrategy> discountStrategiesList) {
        this.discountStrategiesList = discountStrategiesList;
    }

    @Override
    public double calculateDiscount(double totalPrice, int customerID) {
        double totalDiscount = 0.0;
        for (DiscountStrategy strategy : discountStrategiesList) {
            totalDiscount += strategy.calculateDiscount(totalPrice, customerID);
        }
        return totalDiscount;
    }
}
