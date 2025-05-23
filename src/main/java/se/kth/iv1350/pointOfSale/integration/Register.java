package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.model.Sale;
/**
 * The class represent a cash register in the store.
 * It keeps track of the amount of money, and handles payment for the current sale.
 */
public class Register {
    private double balance;
    private double change;

    /**
     * Creates a new instance of the register.
     */
    public Register() {

    }

    /**
     * Register one payment and update the balance in the register and return the change
     * that is handed back to the customer.
     * @param amountPaid is the amount, cash paid by the customer.
     */
    public double registerPayment(double amountPaid, Sale sale) {
        this.balance += amountPaid;

        return amountPaid - sale.getTotalPriceInclVAT();
    }

    /**
     * Method getter for retrieving balance in the register.
     * @return amount balance in the register.
     */
    public double getBalance() {
        return balance;
    }
}
