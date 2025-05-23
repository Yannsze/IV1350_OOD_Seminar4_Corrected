package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.model.TotalDiscountStrategy;

import static org.junit.jupiter.api.Assertions.*;

class TotalDiscountStrategyTest {
    @Test
    public void testTotalDiscount() {
        TotalDiscountStrategy totalDiscountStrategy = new TotalDiscountStrategy();
        double totalPrice = 600.0;
        double discount = totalDiscountStrategy.calculateDiscount(totalPrice,0);

        assertEquals(120, discount,0.001);
    }

    @Test
    public void testTotalDiscountNotApplied() {
        TotalDiscountStrategy totalDiscountStrategy = new TotalDiscountStrategy();
        double totalPrice = 200.0;
        double discount = totalDiscountStrategy.calculateDiscount(totalPrice,0);

        assertEquals(0, discount,0.001);
    }
}