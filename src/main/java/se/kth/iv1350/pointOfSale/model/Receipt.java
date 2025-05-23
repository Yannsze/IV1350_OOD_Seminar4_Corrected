package se.kth.iv1350.pointOfSale.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * A single receipt for one sale.
 * The receipt contains information about the sold items, time of sale,
 * payment, VAT, discounts, and the total amount.
 */
public class Receipt {
    private Sale sale;
    private ArrayList<SoldItem> soldItems;

    /**
     * Creates an instance of a receipt based on a sale.
     * @param sale the sale that this receipt summarizes.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
        this.soldItems = sale.getCurrentSaleList();
    }

    /**
     * Prints the full receipt, including all sold items, total price,
     * VAT, applied discounts, payment information, and change.
     * @return a formatted receipt as a String.
     */
    public String printReceipt() {
        StringBuilder builder = new StringBuilder();
        builder.append("-----   Receipt   -----\n");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatter = dateTime.format(dateAndTimeFormatter);

        builder.append("Time of Sale: " + formatter + "\n\n");

        for (SoldItem soldItem : soldItems) {
            String name = soldItem.getItem().getName();
            int quantity = soldItem.getItemQuantity();
            double unitPrice = soldItem.getItem().getItemPrice();
            double totalPrice = soldItem.getSubTotal();

            builder.append(String.format("%-25s %2dx %.2f   %.2f SEK\n",
                    name, quantity, unitPrice, totalPrice));
        }

        builder.append(String.format("\n%-20s %18.2f SEK\n", "Total:", sale.getTotalPriceInclVAT()));
        builder.append(String.format("%-20s %18.2f SEK\n", "VAT:", (sale.getTotalPriceInclVAT() - sale.getRunningTotal())));

        if (sale.getTotalDiscount() > 0) {
            builder.append(String.format("%-20s %18.2f SEK\n", "Discount:", -sale.getTotalDiscount()));
            builder.append(String.format("%-20s %18.2f SEK\n", "Total after discount:", sale.getDiscountedTotalPrice()));
        }

        builder.append(String.format("%-20s %18.2f SEK\n", "Cash:", sale.getPayment().getAmountPaid()));
        builder.append(String.format("%-20s %18.2f SEK\n", "Change:", sale.getPayment().getChange()));

        builder.append("----- End receipt -----");

        return builder.toString();
    }
}