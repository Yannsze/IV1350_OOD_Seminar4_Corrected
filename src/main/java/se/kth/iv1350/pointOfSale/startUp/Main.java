package se.kth.iv1350.pointOfSale.startUp;

import se.kth.iv1350.pointOfSale.integration.SystemCreator;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.view.TotalRevenueFileOutput;
import se.kth.iv1350.pointOfSale.view.TotalRevenueView;
import se.kth.iv1350.pointOfSale.view.View;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.Register;

/**
 * Starts the entire application. The main method.
 */

public class Main {
    /**
     * This main method starts the entire application.
     * @param args The application does not take any command line parameters.
     */
    public static void main (String[] args) {
        SystemCreator creator = new SystemCreator();
        Register register = new Register();
        Printer printer = new Printer();
        Controller contr = new Controller(register, printer, creator);

        new View(contr).runInitializeSale();
    }
}
