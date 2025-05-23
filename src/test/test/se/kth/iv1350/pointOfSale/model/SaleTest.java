package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.model.CustomerDiscountStrategy;
import se.kth.iv1350.pointOfSale.model.Sale;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.model.SoldItem;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private Sale sale;
    private ItemDTO item;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        item = new ItemDTO("Test Item", 111, "Test description", 10.0, 0.06);
        sale.setDiscountStrategy(new CustomerDiscountStrategy());
    }

    @AfterEach
    void tearDown() {
        sale = null;
        item = null;
    }

    @Test
    void testAddItemUpdatesRunningTotal() {
        sale.addItem(item, 2);
        assertTrue(sale.getRunningTotal() > 0, "Running total should increase after adding items.");

    }

    // new added test for merging items, checking if quantity updates.
    @Test
    void testAddSameItemMergesQuantity() {
        sale.addItem(item, 1);
        sale.addItem(item, 2);
        SoldItem sold = sale.lastAdded();
        assertEquals(3, sold.getItemQuantity(), "Quantity should be merged, when same item is added again.");
    }

    // new added test for checking that the method returns the last added item.
    @Test
    void testLastAddedItemReturnsCorrectItem() {
        sale.addItem(item, 1);
        SoldItem lastAdded = sale.lastAdded();
        assertEquals(item.getItemID(),lastAdded.getItem().getItemID(), "lastAdded should return the most recent added item.");
    }

    // new added test to check that VAT is calculated correctly.
    // delta = allowed margin of error when rounding.
    @Test
    void testVATIncludedInTotalPrice() {
        sale.addItem(item, 2);
        double expected = 20 + 20*0.06;
        assertEquals(expected, sale.getTotalPriceInclVAT(), 0.001, "Total incl. VAT should be calculated correctly.");
    }

    @Test
    void testDiscountCalculation() {
        sale.addItem(item, 2);
        sale.discount(12345);
        assertTrue(sale.getDiscountedTotalPrice() < sale.getTotalPriceInclVAT(), "Discount should lower total price.");
    }

    // new added test to check all discount.
    @Test
    void testAllDiscountApplied() {
        sale.addItem(item, 2);
        sale.discount(12345);
        assertTrue(sale.getDiscountedTotalPrice() > 0);
        assertTrue(sale.getDiscountedTotalPrice() < sale.getTotalPriceInclVAT());
    }

    @Test
    void testPayCreatesPaymentObject() {
        sale.pay(500);
        assertNotNull(sale.getPayment(), "Payment object should not be null after paying.");
    }

    // new added test to check if the payment is the number stored correctly.
    @Test
    void testPaymentStoresCorrectAmount() {
        sale.pay(500);
        assertEquals(500,sale.getPayment().getAmountPaid(),0.01);
    }

    @Test
    void testCreateReceiptNotNull() {
        assertNotNull(sale.createReceipt(), "Receipt should not be null after sale creation.");
    }
}
