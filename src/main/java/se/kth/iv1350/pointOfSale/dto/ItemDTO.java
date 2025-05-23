package se.kth.iv1350.pointOfSale.dto;

/**
 * Contains information about one specific item.
 */
public class ItemDTO {
    private final String name;
    private final int itemID;
    private final String itemDescription;
    private final double itemPrice;
    private final double VAT;

    /**
     * Creates a new instance representing an item.
     * @param name The name of the item, e.g. <code>Potatoe</code>
     * @param itemID The identifying code for the item.
     * @param itemDescription The descriptive information about the item.
     * @param itemPrice The price of the item.
     */
    public ItemDTO(String name, int itemID, String itemDescription, double itemPrice, double VAT){
        this.name = name;
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.VAT = VAT;
    }

    /**
     * The method add all information about an item to a string.
     * @return a string of one item information containing name, itemID, quantity, description and price of an item.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: " + name + ",");
        builder.append("ItemID: " + itemID + ", ");
        builder.append("Item description: " + itemDescription + ", ");
        builder.append("Item price: " + itemPrice + ".");

        return builder.toString();
    }

    /**
     * Method retrieves the name of one item.
     * @return Method returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method retrieves the identify code of one item.
     * @return Method returns the itemID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Method retrieves the description of one item.
     * @return Method returns the itemDescription.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Method retrieves the price of one item.
     * @return Method returns the item price.
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * Method retrieves the VAT for item.
     * @return Method returns VAT for the item.
     */
    public double getVAT() {
        return VAT;
    }
}