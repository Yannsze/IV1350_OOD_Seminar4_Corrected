package test.se.kth.iv1350.pointOfSale.startUp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pointOfSale.controller.Controller;
import se.kth.iv1350.pointOfSale.startUp.Main;
import se.kth.iv1350.pointOfSale.view.View;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private Main instanceToTest;
    private ByteArrayOutputStream printOutSale; // store System print as an array to read.
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        instanceToTest = new Main();

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
    public void testUIhasInitialized() {
        String[] args = null;
        Main.main(args);
        String testPrintInitializeSale = printOutSale.toString();
        String expectedOutput = "initialized";
        assertTrue(testPrintInitializeSale.contains(expectedOutput), "UI did not start correctly");
    }
}