package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.model.CustomerDiscountStrategy;
import se.kth.iv1350.pointOfSale.model.Receipt;
import se.kth.iv1350.pointOfSale.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private Sale sale;
    private ItemDTO item;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        item = new ItemDTO("Test item",111, "Test description",10.0,0.06);
    }

    @AfterEach
    void tearDown() {
        sale = null;
        item = null;
    }

    @Test
    void testPrintReceiptReturnsNonEmptyString() {
        sale.pay(500);
        Receipt receipt = new Receipt(sale);
        assertFalse(receipt.printReceipt().isEmpty(), "Receipt printout should not be empty.");
    }

    @Test
    void testReceiptContainsItemName() {
        sale.addItem(item,1);
        sale.pay(100);
        Receipt receipt = new Receipt(sale);
        String output = receipt.printReceipt();

        assertTrue(output.contains("Test item"), "Receipt should contain the item name.");
    }

    @Test
    void testReceiptIncludesTotalAndVAT() {
        sale.addItem(item, 2);
        sale.pay(100);
        Receipt receipt = new Receipt(sale);
        String output = receipt.printReceipt();

        assertTrue(output.contains("Total: "), "Receipt should contain a total line.");
        assertTrue(output.contains("VAT: "), "Receipt should contain a VAT line.");
    }

    @Test
    void testReceiptIncludeDiscountIfApplied() {
        sale.setDiscountStrategy(new CustomerDiscountStrategy());
        sale.addItem(item, 2);
        sale.discount(12345);
        sale.pay(100);
        Receipt receipt = new Receipt(sale);
        String output = receipt.printReceipt();

        assertTrue(output.contains("Discount: "), "Receipt should contain a discount line.");
        assertTrue(output.contains("Total after discount: "), "Receipt should contain a total after disount line.");
    }

    @Test
    void testReceiptIncludePaymentAndChange() {
        sale.addItem(item, 1);
        sale.pay(100);
        Receipt receipt = new Receipt(sale);
        String output = receipt.printReceipt();

        assertTrue(output.contains("Cash: "), "Receipt should contain a cash line.");
        assertTrue(output.contains("Change: "), "Receipt should contain a change line.");
    }
}
