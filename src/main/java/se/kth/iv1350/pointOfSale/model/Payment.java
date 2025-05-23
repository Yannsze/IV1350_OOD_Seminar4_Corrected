package se.kth.iv1350.pointOfSale.model;

/**
 * A single payment for one sale.
 */
public class Payment {
    private double amountPaid;
    private double change;

    /**
     * Creates an instance of one payment.
     *
     * @param amountPaid represent the amount that is paid.
     */
    public Payment(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Gets the amount paid by the customer.
     * @return the amount paid.
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Gets the change to be given back to the customer.
     * @return the change amount.
     */
    public double getChange() {
        return change;
    }

    /**
     * Sets the change to be given back to the customer.
     * @param change the change amount to set.
     */
    public void setChange(double change) {
        this.change = change;
    }
}