package se.kth.iv1350.pointOfSale.integration;

import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.model.SoldItem;

import java.util.ArrayList;

/**
 * This is the external inventory system that stores information about items. Name, ID, description + (tax rate)
 * and price.
 */
public class InventorySystem {

    ItemDTO itemOne = new ItemDTO("Bigwheel Oatmeal", 123,
            "BigWheel Oatmeal 500 g, whole grain oats, 7 high fiber, gluten free", 29.90, 0.06);
    ItemDTO itemTwo = new ItemDTO("YouGoGo Blueberry", 456,
            "YouGoGo Blueberry 240 g, low sugar youghurt, blueberry flavour", 14.90, 0.06);

    private final ItemDTO[] itemList = {itemOne, itemTwo};
    private boolean throwDatabaseConnectionException;

    /**
     *
     * The method looks through number of items in the inventory system and check if it's in the database.
     * @param itemIdentity takes the input parameter of the item from user interface.
     * @return the itemDTO if the item is found, else null.
     * @throws ItemInvalidException ItemInvalidException If no item with the specified ID is found in the inventory.
     * @throws DatabaseConnectionException If there is a failure in connecting to the database.
     */
    public ItemDTO itemFound (int itemIdentity) throws ItemInvalidException, DatabaseConnectionException {
        if (throwDatabaseConnectionException) {
            throw new DatabaseConnectionException("Database is not running.");
        }
        if (itemIdentity == 999) {
            throw new DatabaseConnectionException("Database is not running. Could not search for item.");
        }
        for (ItemDTO item: itemList) {
            if (itemIdentity == item.getItemID()) {
                return item;
            }
        }
        throw new ItemInvalidException("Item not found");
    }

    /**
     * Update the inventory system of item sold.
     * @param soldItems a list of sold items from a single sale.
     */
    public void updateInventorySystem(ArrayList<SoldItem> soldItems) {

    }
}