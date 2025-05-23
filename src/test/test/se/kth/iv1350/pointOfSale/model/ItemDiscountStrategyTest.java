package test.se.kth.iv1350.pointOfSale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.model.ItemDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDiscountStrategyTest {
    @Test
    public void testItemDiscountApplied() {
        List<Integer> items = List.of(123);
        ItemDiscountStrategy itemDiscountStrategy = new ItemDiscountStrategy(items);

        double totalPrice = 200.0;
        double discount = itemDiscountStrategy.calculateDiscount(totalPrice,0);

        assertEquals(10.0, discount, 0.001);
    }

    @Test
    public void testNoDiscountInvalidItem() {
        List<Integer> items = List.of(888);
        ItemDiscountStrategy itemDiscountStrategy = new ItemDiscountStrategy(items);

        double totalPrice = 200.0;
        double discount = itemDiscountStrategy.calculateDiscount(totalPrice,0);

        assertEquals(0.0, discount, 0.001);
    }

    @Test
    public void testEmptyItemList() {
        List<Integer> items = new ArrayList<>();
        ItemDiscountStrategy itemDiscountStrategy = new ItemDiscountStrategy(items);

        double totalPrice = 200.0;
        double discount = itemDiscountStrategy.calculateDiscount(totalPrice,0);

        assertEquals(0.0, discount, 0.001);
    }
}