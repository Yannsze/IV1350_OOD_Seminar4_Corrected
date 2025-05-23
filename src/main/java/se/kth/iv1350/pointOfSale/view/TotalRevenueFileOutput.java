package se.kth.iv1350.pointOfSale.view;

import se.kth.iv1350.pointOfSale.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Prints a log message to a file. The log file will be in the same directory called "TotalRevenueFileOutput".
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private PrintWriter totalRevenueFileLogger;

    /**
     * Creates a new instance, and a new log file. The old one will be deleted.
     */
    public TotalRevenueFileOutput() {
        try {
            totalRevenueFileLogger = new PrintWriter(new FileWriter("TotalRevenueFileOutput.txt"), true);
        } catch (IOException ioException) {
            System.out.println("Can not be logged");
            ioException.printStackTrace();
        }
    }

    @Override
    public void newTotalPrice(double newTotalPrice) {
        totalRevenueFileLogger.format("Total income: %.2f SEK\n", newTotalPrice);
    }
}
