package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountDatabaseTest {

    @Test
    void testGetCustomerDiscount() {
        DiscountDatabase db = DiscountDatabase.getInstance();
        double discount = db.getCustomerDiscount(12345);
        assertEquals(0.10, discount, "Customer with ID 12345 should get 10% discount.");
    }

    @Test
    void testGetItemDiscount() {
        DiscountDatabase db = DiscountDatabase.getInstance();
        double discount = db.getItemDiscount(123);
        assertEquals(0.05, discount, "Item with ID 123 should get 5% discount.");
    }

    @Test
    void testGetTotalDiscount() {
        DiscountDatabase db = DiscountDatabase.getInstance();
        double discount = db.getTotalDiscount(600);
        assertEquals(0.20, discount, "Total price above 500 should get 20% discount.");
    }
}
