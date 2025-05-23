package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.integration.DatabaseConnectionException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionExceptionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testErrorWithMessage() {
        String msg = "Database connection failed";
        DatabaseConnectionException ex = new DatabaseConnectionException(msg);

        assertEquals(msg, ex.getMessage(), "Exception message should match the input message.");
        assertNull(ex.getCause(), "Cause should be null when only message is provided.");
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String msg = "Database error";
        Exception cause = new Exception("Underlying IO error");
        DatabaseConnectionException ex = new DatabaseConnectionException(msg, cause);

        assertEquals(msg, ex.getMessage(), "Exception message should match the input message.");
        assertEquals(cause, ex.getCause(), "Cause should match the one provided.");
    }
}