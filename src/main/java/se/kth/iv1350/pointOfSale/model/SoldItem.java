package se.kth.iv1350.pointOfSale.model;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;

/**
 * This class contains information about item and its quantity.
 */
public class SoldItem {
    private ItemDTO item;
    private int itemQuantity;

    /**
     * The method create a new instance representing the item that is sold and how many (quantity).
     * @param item is the item scanned and added to the sold item list.
     * @param itemQuantity is the number of the same item added.
     */
    public SoldItem(ItemDTO item, int itemQuantity) {
        this.item = item;
        this.itemQuantity = itemQuantity;
    }

    /**
     * The method retrieves the itemDTO that has been sold.
     * @return the itemDTO.
     */
    public ItemDTO getItem() {
        return item;
    }

    /**
     * The method retrieves the number of the same item that is purchased.
     * @return the item's quantity.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * The method increases the number of quantity if more than one is being sold.
     * @param increaseQuantity takes the input of number of the same item being sold.
     */
    public void increaseQuantity(int increaseQuantity) {
        this.itemQuantity += increaseQuantity;
    }

    /**
     * The method is used to get the sub-total of sold item and number of quantity.
     * @return the subtotal of an item.
     */
    public double getSubTotal() {
        return item.getItemPrice() * itemQuantity;
    }

    /**
     * Get the price for sold item and its quantity including VAT.
     * @return price of total sold item of that kind with VAT.
     */
    public double getVATAmount() {
        return item.getVAT() * getSubTotal();
    }
}
