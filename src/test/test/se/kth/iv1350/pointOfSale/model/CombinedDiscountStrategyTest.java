package test.se.kth.iv1350.pointOfSale.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;
import se.kth.iv1350.pointOfSale.model.*;

import java.util.Arrays;
import java.util.List;

class CombinedDiscountStrategyTest {
    private CombinedDiscountStrategy combinedDiscountStrategy;
    private DiscountDatabase discountDatabase;

    @BeforeEach
    void setup() {
        DiscountStrategy customerStrategy = new CustomerDiscountStrategy();
        DiscountStrategy totalStrategy = new TotalDiscountStrategy();
        DiscountStrategy itemStrategy = new ItemDiscountStrategy(Arrays.asList(123, 456)); // example item IDs

        List<DiscountStrategy> strategies = Arrays.asList(customerStrategy, totalStrategy, itemStrategy);
        combinedDiscountStrategy = new CombinedDiscountStrategy(strategies);
    }

    @AfterEach
    void tearDown() {
        combinedDiscountStrategy = null;
    }

    @Test
    void testCalculateDiscount_combinesAllDiscounts() {
        discountDatabase = DiscountDatabase.getInstance();
        double totalPrice = 1000.0;
        int customerID = 12345;

        double discount = combinedDiscountStrategy.calculateDiscount(totalPrice, customerID);
        double expectedCustomerDiscount = totalPrice * discountDatabase.getCustomerDiscount(customerID);
        double expectedTotalDiscount = totalPrice * discountDatabase.getTotalDiscount(totalPrice);

        double expectedItemDiscount = 0.0;
        for (int id : Arrays.asList(123, 456)) {
            expectedItemDiscount += totalPrice * discountDatabase.getItemDiscount(id);
        }

        double expectedTotalDiscountAmount = expectedCustomerDiscount + expectedTotalDiscount + expectedItemDiscount;
        assertEquals(expectedTotalDiscountAmount, discount, 0.001, "Combined discount should equal sum of individual discounts");
    }
}
