package se.kth.iv1350.pointOfSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.DiscountDatabase;
import se.kth.iv1350.pointOfSale.view.TotalRevenueFileOutput;
import se.kth.iv1350.pointOfSale.view.TotalRevenueView;

/**
 * One single sale made by one customer, and paid with one payment.
 */
public class Sale {
    private double runningTotal;
    private double VAT;
    private double totalPriceInclVAT;
    private double totalDiscount = 0.0;
    private double discountedTotalPrice = 0.0;
    private Payment payment;
    private ArrayList<SoldItem> currentSaleList;
    private LocalDateTime dateTime;
    private List<TotalRevenueObserver> totalPriceObservers = new ArrayList<>();
    private DiscountStrategy discountStrategy; // Added this field

    /**
     * Creates a new instance of Sale with an empty list, containing items.
     */
    public Sale() {
        currentSaleList = new ArrayList<SoldItem>();
        dateTime = LocalDateTime.now();
        VAT = 0.06;
    }

    /**
     * The method provides access to the current sale list.
     * @return current sale list containing array list of sold items.
     */
    public ArrayList<SoldItem> getCurrentSaleList() {
        return currentSaleList;
    }

    /**
     * Gets a list of item IDs for all items in the current sale.
     * @return a list of item IDs.
     */
    public List<Integer> getItemIDs() {
        List<Integer> itemIDs = new ArrayList<>();
        for (SoldItem soldItem : currentSaleList) {
            itemIDs.add(soldItem.getItem().getItemID());
        }
        return itemIDs;
    }
    /**
     * Gets the running total of the sale, excluding VAT.
     * @return the running total price without VAT.
     */
    public double getRunningTotal () {
        return this.runningTotal;
    }

    /**
     * Gets the total price including VAT.
     * @return the total price with VAT included.
     */
    public double getTotalPriceInclVAT() {
        return this.totalPriceInclVAT;
    }

    /**
     * Gets the total discount applied to the sale.
     * @return the total discount amount.
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Gets the total price after all discounts have been applied.
     * @return the discounted total price.
     */
    public double getDiscountedTotalPrice() {
        return discountedTotalPrice;
    }

    /**
     * Gets the payment associated with this sale.
     * @return the payment object.
     */
    public Payment getPayment() {
        return this.payment;
    }

    /**
     * Adds an item to the current sale.
     * If the item already exists in the list, its quantity is increased instead of adding a new entry.
     *
     * @param item the item being added to the sale.
     * @param quantity the quantity of the item to add.
     */
    public void addItem (ItemDTO item, int quantity) {
        for (int i = 0; i < currentSaleList.size(); i++) {
            SoldItem soldItem = currentSaleList.get(i);
            if (soldItem.getItem().getItemID() == item.getItemID()) {
                double previousSubTotal = soldItem.getSubTotal();
                soldItem.increaseQuantity(quantity);
                double newSubTotal = soldItem.getSubTotal();
                runningTotal += (newSubTotal - previousSubTotal);
                totalPriceInclVAT = runningTotal + runningTotal*VAT;
                return;
            }
        }

        SoldItem newItem = new SoldItem(item, quantity);
        currentSaleList.add(newItem);
        runningTotal += newItem.getSubTotal();
        totalPriceInclVAT = runningTotal + runningTotal*VAT;
    }

    /**
     * Retrieves the last item that was added to the sale.
     * @return the last added sold item.
     */
    public SoldItem lastAdded () {
        return currentSaleList.get(currentSaleList.size() - 1);
    }

    /**
     * Sets the discount strategy to be used for calculating discounts.
     * @param strategy the discount strategy.
     */
    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    /**
     * Applies discounts to the sale based on customer ID and the set discount strategy.
     * @param customerID the ID of the customer eligible for discount.
     */
    public void discount(int customerID) {
        if (discountStrategy == null) {
            System.out.println("No discount strategy set. No discount applied.");
            totalDiscount = 0.0;
            discountedTotalPrice = totalPriceInclVAT;
            return;
        }
        totalDiscount = discountStrategy.calculateDiscount(runningTotal, customerID);
        discountedTotalPrice = totalPriceInclVAT - totalDiscount;
    }

    /**
     * Registers a payment for the sale.
     * @param amountPaid the amount paid by the customer.
     */
    public void pay(double amountPaid) {
        this.payment = new Payment(amountPaid);
        notifyObservers();
    }

    /**
     * Creates a receipt for the completed sale.
     * @return the generated receipt.
     */
    public Receipt createReceipt() {
        return new Receipt(this);
    }

    /**
     * Adds an observer that will be notified of updates to the total price.
     * @param observer The observer to be added.
     */
    public void addTotalObserver(TotalRevenueObserver observer){
        totalPriceObservers.add(observer);
    }

    /**
     * Notifies all registered observers with the updated total price including VAT.
     * This method is called whenever the sale total changes.
     */
    public void notifyObservers(){
        for(TotalRevenueObserver obs : totalPriceObservers){
            obs.newTotalPrice(totalPriceInclVAT);
        }
    }
}