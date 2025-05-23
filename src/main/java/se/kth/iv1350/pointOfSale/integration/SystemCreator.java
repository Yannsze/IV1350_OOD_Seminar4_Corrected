package se.kth.iv1350.pointOfSale.integration;

/**
 * This initializes the system of POS.
 */
public class SystemCreator {
    private AccountingSystem accountingSystem = new AccountingSystem();
    private InventorySystem inventorySystem = new InventorySystem();
    private DiscountDatabase discountDatabase = DiscountDatabase.getInstance();

    /**
     * Get the value of accounting system.
     *
     * @return the value of accounting system.
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * Get the value of inventory system.
     *
     * @return the value of inventory system.
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    /**
     * Get the value of discount database.
     *
     * @return the value of database.
     */
    public DiscountDatabase getDiscountDatabase() {
        return discountDatabase;
    }
}
