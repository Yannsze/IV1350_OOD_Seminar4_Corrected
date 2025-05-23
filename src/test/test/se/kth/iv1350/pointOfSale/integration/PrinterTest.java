package test.se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Receipt;

/**
 * Printer for printing out the receipt.
 */
public class PrinterTest {

    /**
     * Prints the provided receipt.
     * @param receipt contains information about one sale.
     */
    public void printReceipt(Receipt receipt) {
        System.out.println(receipt.printReceipt());
    }
}