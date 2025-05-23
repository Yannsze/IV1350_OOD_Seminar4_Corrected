package se.kth.iv1350.pointOfSale.integration;

import java.util.ArrayList;

/**
 * This is the external discount database that stores information about the customer.
 * It contains three types of discount that can be applied, one is to the item, second is to the total sale and
 * the third is to the customer.
 */
public class DiscountDatabase {
    private static DiscountDatabase instance; // Singleton instance

    Integer customerIDOne = new Integer(12345);
    Integer customerIDTwo = new Integer (56789);
    Integer customerIDThree = new Integer (11213);
    Integer customerIDFour = new Integer (14151);

    private final ArrayList<Integer> customerList = new ArrayList<Integer>();

    /**
     * Contains a list of customer's ID.
     * @return the list of every customer's ID.
     */
    public ArrayList<Integer> getCustomerList() {
        return customerList;
    }

    /**
     * Private constructor for singleton pattern.
     */
    private DiscountDatabase() {
        customerList.add(customerIDOne);
        customerList.add(customerIDTwo);
        customerList.add(customerIDThree);
        customerList.add(customerIDFour);
    }

    /**
     * Returns the singleton instance of DiscountDatabase.
     */
    public static DiscountDatabase getInstance() {
        if (instance == null) {
            instance = new DiscountDatabase();
        }
        return instance;
    }

    /**
     * Method getter to get customer discount.
     * @param customerID the customer ID stored in discount database.
     * @return 10% discount if customer is a member, else return 0.
     */
    public double getCustomerDiscount(int customerID) {
        if (customerList.contains(customerID)) {
            return 0.10;
        }
        return 0.0;
    }

    /**
     * Method getter to get item discount.
     * @param itemID the item ID.
     * @return 5% discount if the item is bought, else return 0.
     */
    public double getItemDiscount(int itemID) {
        if (itemID == 123) {
            return 0.05;
        }
        return 0.0;
    }

    /**
     * Method getter to get total discount applied on the entire sale.
     * @param totalPrice the total price of the sale (excluding VAT, since discount cannot be applied on VAT).
     * @return 20% if total price of sale is above 500 SEK, else return 0.
     */
    public double getTotalDiscount(double totalPrice) {
        if (totalPrice > 500) {
            return 0.20;
        }
        return 0.0;
    }
}