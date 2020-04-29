package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemSold;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.SaleInfoDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RecieptTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    void makeRecieptToString() {
        Sale sale = new Sale();
        ItemSold itemSold = new ItemSold(new ItemDTO(10,"Candy",6,10), 4);
        sale.registerItem(itemSold);
        sale.calculateChange(100);
        SaleInfoDTO saleInfo = sale.createSaleInfoDTO(new RetailStore("test","store"));
        Reciept reciept = new Reciept(saleInfo);
        Printer printer = new Printer();
        printer.print(reciept.makeRecieptToString());

        String result = outContent.toString();

        assertTrue(result.contains(itemSold.getItem().getItemName()), "Item not showing.");
        assertTrue(result.contains(Integer.toString(itemSold.getItem().getItemPrice())), "Item not showing.");
        assertTrue(result.contains(Integer.toString(itemSold.getQuantity())), "Item quantity not showing.");
        assertTrue(result.contains("Total price: 40"), "Total price not showing.");
        assertTrue(result.contains("Change: 60"), "Change not showing.");
        assertTrue(result.contains("2020"), "Date not showing.");
        assertTrue(result.contains("test"));
    }
}