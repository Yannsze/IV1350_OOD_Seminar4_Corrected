package test.se.kth.iv1350.pointOfSale.dto;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDTOTest {

    @Test
    void testGetters() {
        ItemDTO item = new ItemDTO("Apple", 1, "Fresh apple", 5.0, 0.06);
        assertEquals(1, item.getItemID(), "Item ID should match.");
        assertEquals("Apple", item.getName(), "Item name should match.");
    }

    @Test
    void testToStringNotEmpty() {
        ItemDTO item = new ItemDTO("Apple", 1, "Fresh apple", 5.0, 0.06);
        assertFalse(item.toString().isEmpty(), "Item toString() should return a non-empty string.");
    }
}
