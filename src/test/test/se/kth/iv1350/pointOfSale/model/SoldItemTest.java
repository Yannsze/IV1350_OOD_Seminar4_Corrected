package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.model.SoldItem;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

public class SoldItemTest {

    @Test
    void testIncreaseQuantity() {
        ItemDTO item = new ItemDTO("Bread", 321, "Bread description", 20.0, 0.06);
        SoldItem soldItem = new SoldItem(item, 1);
        soldItem.increaseQuantity(2);
        assertEquals(3, soldItem.getItemQuantity(), "Item quantity should correctly increase.");
    }

    @Test
    void testGetSubTotal() {
        ItemDTO item = new ItemDTO("Bread", 321, "Bread description", 20.0, 0.06);
        SoldItem soldItem = new SoldItem(item, 2);
        assertEquals(40.0, soldItem.getSubTotal(), "Subtotal should be item price times quantity.");
    }
}
