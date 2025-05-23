package test.se.kth.iv1350.pointOfSale.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.*;
import se.kth.iv1350.pointOfSale.model.SoldItem;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private Controller contr;

    @BeforeEach
    void setUp() {
        SystemCreator systemCreator = new SystemCreator();
        Register register = new Register();
        Printer printer = new Printer();
        contr = new Controller(register, printer, systemCreator);
        contr.initializeSale();
    }

    @AfterEach
    void tearDown() {
        contr = null;
    }

    // new added test to initialize sale.
    @Test
    void testInitializeSaleCreatesNewSale() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.scanItem(123,1); // no exception means Sale is active
        assertEquals(1, contr.getSoldItems().size(), "Sale should contain 1 item after adding.");
    }

    // new added test to scan correct item.
    @Test
    void testScanValidItemAddsCorrectItem() throws DatabaseConnectionException, ItemInvalidException, IOException {
        ItemDTO item = contr.scanItem(123,1);
        assertNotNull(item, "Returned item should not be null for valid ID");
        assertEquals(123, item.getItemID(), "Returned item should match item ID");
    }

    // new added test to get sold item list after scanning.
    @Test
    void testGetSoldItemAfterScan() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.scanItem(123,2);
        ArrayList<SoldItem> soldItems = contr.getSoldItems();
        assertEquals(1, soldItems.size(), "Should return one sold item.");
        assertEquals(2, soldItems.get(0).getItemQuantity(), "Quantity should be 2");
    }

    // new added test for correct running total after added item.
    @Test
    void testRunningTotalAfterAddedItems() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.scanItem(123,2);
        double runningTotal = contr.runningTotal();
        assertTrue(runningTotal > 0, "Running total should be greater than 0 after item is added");
    }

    @Test
    void testEndSaleReturnsTotalInclVAT() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.scanItem(123,2);
        double totalInclVAT = contr.endSale();
        assertTrue(totalInclVAT > 0, "Total with VAT should be calculated and > 0");
    }

    @Test
    void testScanItemDoesNotThrow() {
        assertDoesNotThrow(() -> {
            contr.scanItem(123, 1);
        }, "Adding a valid item should not throw an exception.");
    }

    @Test
    void testApplyCustomerDiscountLowersTotal() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.initializeSale();
        contr.scanItem(123, 1);
        double before = contr.endSale();
        contr.applyCustomerDiscount(12345);
        double after = contr.getDiscountedTotal();
        assertTrue(after < before, "Total should be lowered after discount.");
    }

    // removed from controller because it's already tested in side each specific classes
    /*
    @Test
    void testApplyTotalDiscountLowersTotal() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.initializeSale();
        contr.scanItem(123, 20);
        double before = contr.endSale();
        contr.applyTotalDiscount();
        double after = contr.getDiscountedTotal();
        assertTrue(after < before, "Total should be lowered after discount.");
    }

    @Test
    void testApplyItemDiscountLowersTotal() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.initializeSale();
        contr.scanItem(123, 1);
        double before = contr.endSale();
        contr.applyItemDiscount();
        double after = contr.getDiscountedTotal();
        assertTrue(after < before, "Total should be lowered after discount.");
    }

     */

    @Test
    void testInitializePaymentRegistersCorrectly() throws DatabaseConnectionException, ItemInvalidException, IOException {
        contr.scanItem(123, 1);
        contr.initializePayment(100);
        // No exceptions thrown = pass (for now, as system output can't be tested directly)
    }

    //Exception tests
    @Test
    void testScanInvalidItemThrowsItemInvalidException() {
        assertThrows(ItemInvalidException.class, () -> {
            contr.scanItem(9999, 1);
        });
    }

    @Test
    void testScanItemDatabaseFailureThrowsDatabaseConnectionException() {
        assertThrows(DatabaseConnectionException.class, () -> {
            contr.scanItem(999, 1); // The ID that triggers a database exception
        });
    }
}