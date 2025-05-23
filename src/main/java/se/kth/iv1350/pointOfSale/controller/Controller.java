package se.kth.iv1350.pointOfSale.controller;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.*;
import se.kth.iv1350.pointOfSale.model.*;
import se.kth.iv1350.pointOfSale.util.LogHandler;
import se.kth.iv1350.pointOfSale.view.TotalRevenueView;
import se.kth.iv1350.pointOfSale.view.TotalRevenueFileOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Register register;
    private Printer printer;
    private Receipt receipt;
    private Sale sale;
    private SystemCreator systemCreator;
    private LogHandler logger;
    private TotalRevenueView totalRevenueView = new TotalRevenueView();
    private TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
    private List<TotalRevenueObserver> totalRevenueObserver;


    /**
     * Create a new instance of Controller class with specified register and printer.
     * @param register is used to track the cash.
     * @param printer is used to print the receipt at the end of the sale.
     */
    public Controller(Register register, Printer printer, SystemCreator systemCreator) {
        this.register = register;
        this.printer = printer;
        this.systemCreator = systemCreator;
        try {
            this.logger = new LogHandler();
        } catch (IOException e) {
            System.out.println("CAN NOT LOG.");
            e.printStackTrace();
        }
    }

    /**
     * The method retrieves list of sold items in sale.
     * @return the list of sold item.
     */
    public ArrayList<SoldItem> getSoldItems() {
        return sale.getCurrentSaleList();
    }

    /**
     * It's a getter for retrieving discounted price of the sale.
     * @return discounted total price of the sale.
     */
    public double getDiscountedTotal() {
        return sale.getDiscountedTotalPrice();
    }

    /**
     * Initialize a new sale. This method is called before the entire sale process.
     */
    public void initializeSale() {
        sale = new Sale();
    }

    /**
     * The method save the item from the inventory system and adds it to the sale if it's found.
     * @param itemID is the input parameter from user interface when identifying the items.
     * @param quantity is the input parameter from the user interface setting the quantity of the item.
     * @return null if no such item exists in the inventory system, returns the ItemDTO to string if it's found.
     */
    public ItemDTO scanItem (int itemID, int quantity) throws ItemInvalidException, DatabaseConnectionException, IOException {
        try {
            ItemDTO item = systemCreator.getInventorySystem().itemFound(itemID);
            sale.addItem(item, quantity);
            return item;
        } catch (ItemInvalidException itemInvalidException) {
            logger.logException(itemInvalidException);
            throw new ItemInvalidException("Item ID " + itemID + " cannot be found.", itemInvalidException);
        } catch (DatabaseConnectionException databaseConnectionException) {
            logger.logException(databaseConnectionException);
            throw new DatabaseConnectionException("Database cannot be connected", databaseConnectionException);
        }
    }

    /**
     * The method gets the running total of the sale.
     * @return the running total.
     */
    public double runningTotal() {
        return sale.getRunningTotal();
    }

    /**
     * Ends the sale by returning the total price including VAT.
     * @return total price including VAT of the sale.
     */
    public double endSale() {
        return sale.getTotalPriceInclVAT();
    }

    /**
     * Applies discount to the sale.
     * @param customerID the customer ID for retrieving potential discounts.
     */
    /* Removed after the implementation of the strategy interface.
    public void applyDiscount(int customerID) {
        sale.discount(customerID);
    }
     */

    /**
     * Applies a discount to the sale, based on customerID.
     * @param customerID the customer ID for retrieving potential discounts.
     */
    public void applyCustomerDiscount(int customerID) {
        sale.setDiscountStrategy(new CustomerDiscountStrategy());
        sale.discount(customerID);
    }

    /**
     * Applies a total discount to the sale.
     */
    public void applyTotalDiscount() {
        sale.setDiscountStrategy(new TotalDiscountStrategy());
        double total = sale.getRunningTotal();
        sale.discount(0);
    }

    /**
     * Applies an item discount to the sale.
     */
    public void applyItemDiscount() {
        sale.setDiscountStrategy(new ItemDiscountStrategy(sale.getItemIDs()));
        sale.discount(0);
    }

    /**
     * Applies every discount to the sale.
     * @param customerID
     */
    public void applyCombinedDiscounts(int customerID) {
        List<DiscountStrategy> strategies = new ArrayList<>();
        strategies.add(new CustomerDiscountStrategy());
        strategies.add(new TotalDiscountStrategy());
        strategies.add(new ItemDiscountStrategy(sale.getItemIDs()));

        sale.setDiscountStrategy(new CombinedDiscountStrategy(strategies));
        sale.discount(customerID);
    }

    /**
     * Initializes the payment for one single sale, updates the external systems, creates the print the receipt.
     * @param amountPaid the amount in cash that is paid for the sale.
     */
    public void initializePayment(double amountPaid) {
        sale.pay(amountPaid);
        systemCreator.getInventorySystem().updateInventorySystem(sale.getCurrentSaleList());
        systemCreator.getAccountingSystem().recordTransaction(sale.getCurrentSaleList());
        double change = register.registerPayment(amountPaid, sale);
        sale.getPayment().setChange(change);
        receipt = sale.createReceipt();
        printer.printReceipt(receipt);
    }

    /**
     * Adds the passed observer to the observer list
     * @param observer takes the observer from sale.
     */
    public void addObservers(TotalRevenueObserver observer) {
        sale.addTotalObserver(observer);
    }
}