package se.kth.iv1350.pos.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.RetailStore;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    private View testInstance;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        AccountingSystem acctSys = new AccountingSystem();
        SaleLog saleLog = new SaleLog();
        DiscountCatalog discCat = new DiscountCatalog();
        RetailStore retStore = new RetailStore("Name", "Address");
        ItemInventory itemInv = new ItemInventory();
        Printer printer = new Printer();
        ExternalSystemHandler extSysHan = new ExternalSystemHandler(saleLog, itemInv, acctSys, printer);
        Controller contr = new Controller(extSysHan, discCat, retStore);
        testInstance = new View(contr);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        testInstance = null;

        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testSimulatePurchase() {
        testInstance.simulatePurchase();
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI started unsuccessfully");
    }
}