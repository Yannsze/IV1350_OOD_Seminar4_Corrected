package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.integration.ItemInvalidException;

import static org.junit.jupiter.api.Assertions.*;

class ItemInvalidExceptionTest {
    @Test
    void testErrorWithMessage () {
        String msg = "ItemID is invalid";
        ItemInvalidException itemInvalidException = new ItemInvalidException(msg);

        assertEquals(msg, itemInvalidException.getMessage(), "Exception should match the input message.");
        assertNull(itemInvalidException.getCause(), "Cause should be null when only message is provided.");
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String msg = "Invalid item ID";
        Exception cause = new Exception("Item lookup failed");
        ItemInvalidException ex = new ItemInvalidException(msg, cause);

        assertEquals(msg, ex.getMessage(), "Exception message should match the input message.");
        assertEquals(cause, ex.getCause(), "Cause should match the one provided.");
    }
}