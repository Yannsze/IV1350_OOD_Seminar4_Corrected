package test.se.kth.iv1350.pointOfSale.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.integration.Register;
import se.kth.iv1350.pointOfSale.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {

    @Test
    void testRegisterPaymentCalculatesChange() {
        Register register = new Register();
        Sale sale = new Sale();
        sale.addItem(new se.kth.iv1350.pointOfSale.dto.ItemDTO("Milk", 222, "Milk 1L", 10.0, 0.06), 2);
        double change = register.registerPayment(50, sale);
        assertTrue(change > 0, "Change should be greater than zero if customer paid more.");
    }
}
