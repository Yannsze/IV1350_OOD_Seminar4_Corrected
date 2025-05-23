package se.kth.iv1350.pointOfSale.model;

/**
 * An observer interface for receiving notification about the new total sum of a sale.
 * The class that is interested in such notification implements this interface.
 */
public interface TotalRevenueObserver {
    /**
     * Invoked when the sale has ended to show the total sum.
     * @param newTotalPrice
     */
    void newTotalPrice (double newTotalPrice);
}