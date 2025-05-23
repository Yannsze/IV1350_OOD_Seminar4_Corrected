package test.se.kth.iv1350.pointOfSale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.integration.Printer;
import se.kth.iv1350.pointOfSale.integration.Register;
import se.kth.iv1350.pointOfSale.integration.SystemCreator;
import se.kth.iv1350.pointOfSale.view.View;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printOutSale; // store System print as an array to read.
    private PrintStream originalSysOut;
    private Register registerTest;
    private Printer printerTest;
    private SystemCreator systemCreatorTest;

    @BeforeEach
    public void setUp() {
        systemCreatorTest = new SystemCreator();
        registerTest = new Register();
        printerTest = new Printer();

        Controller contr = new Controller(registerTest, printerTest, systemCreatorTest);
        instanceToTest = new View(contr);
        printOutSale = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutSale);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;

        printOutSale = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testRunInitializeSale() {
        instanceToTest.runInitializeSale();
        String testPrintInitializeSale = printOutSale.toString();
        String expectedOutput = "initialized";
        assertTrue(testPrintInitializeSale.contains(expectedOutput), "UI did not start correctly");
    }
}