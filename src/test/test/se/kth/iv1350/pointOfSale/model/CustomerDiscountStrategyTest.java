package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;
import se.kth.iv1350.pointOfSale.model.CustomerDiscountStrategy;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDiscountStrategyTest {
    @Test
    public void customerDiscountStrategy() {
        DiscountDatabase discountDatabase = DiscountDatabase.getInstance();
        CustomerDiscountStrategy customerDiscountStrategy = new CustomerDiscountStrategy();
        double totalPrice = 1000.0;
        int eligibleCustomerID = 12345;
        double discount = customerDiscountStrategy.calculateDiscount(totalPrice, eligibleCustomerID);

        assertEquals(100, discount, 0.001);
    }

    @Test
    public void customerGetsNoDiscount() {
        DiscountDatabase discountDatabase = DiscountDatabase.getInstance();
        CustomerDiscountStrategy customerDiscountStrategy = new CustomerDiscountStrategy();
        double totalPrice = 1000.0;
        int notEligibleCustomerID = 99999;
        double discount = customerDiscountStrategy.calculateDiscount(totalPrice, notEligibleCustomerID);

        assertEquals(0.0, discount, 0.001);
    }
}