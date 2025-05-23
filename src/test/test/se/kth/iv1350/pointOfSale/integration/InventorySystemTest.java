package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.dto.ItemDTO;
import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;
import se.kth.iv1350.pointOfSale.integration.InventorySystem;
import se.kth.iv1350.pointOfSale.integration.ItemInvalidException;

import static org.junit.jupiter.api.Assertions.*;

public class InventorySystemTest {
    private InventorySystem inventory;

    @BeforeEach
    public void setUp() {
        inventory = new InventorySystem();
    }

    @Test
    void testExistingItem() {
        int validItemID = 123;
        try {
            ItemDTO actualItem = inventory.itemFound(validItemID);
            assertEquals(123, actualItem.getItemID());
            assertEquals("Bigwheel Oatmeal", actualItem.getName());
        } catch (ItemInvalidException | DatabaseConnectionException e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testItemNotFoundThrowsItemInvalidException() {
        InventorySystem inventory = new InventorySystem();
        assertThrows(ItemInvalidException.class, () -> {
            inventory.itemFound(987); // 987 should be an invalid ID
        });
    }

    @Test
    void testDatabaseFailureThrowsDatabaseConnectionException() {
        InventorySystem inventory = new InventorySystem();
        assertThrows(DatabaseConnectionException.class, () -> {
            inventory.itemFound(999); // ID that triggers DatabaseConnectionException
        });
    }

}
