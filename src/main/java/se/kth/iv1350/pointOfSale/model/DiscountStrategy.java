package se.kth.iv1350.pointOfSale.model;

/**
 * A strategy interface for applying different types of discounts.
 * Implementations of this interface define how a discount is calculated
 * based on the total price and customer information.
 */
public interface DiscountStrategy {
    /**
     * Calculates the discount to be applied based on the given total price and customer ID.
     * @param totalPrice The total price before discount.
     * @param customerID The ID of the customer to determine if the customer is eligible for discount.
     * @return The calculated discount amount (not the final total).
     */
    double calculateDiscount(double totalPrice, int customerID);
}