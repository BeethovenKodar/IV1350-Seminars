package se.kth.iv1350.pos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.integration.Item;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemSold;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    private Sale saleInstance;


    @BeforeEach
    void setUp() {
        saleInstance = new Sale();
    }

    @AfterEach
    void tearDown() {
        saleInstance = null;
    }

    @Test
    void registerItemNotDuplicate() {
        ItemDTO itemDTO = new ItemDTO(10, "Banana", 6, 111);
        ItemSold itemSold = new ItemSold(itemDTO, 4);
        saleInstance.registerItem(itemSold);
        assertTrue(saleInstance.getBoughtItems().contains(itemSold), "Item was not added.");
    }

    @Test
    void registerDuplicateItem() {
        ItemDTO itemDTOFirst = new ItemDTO(10, "Banana", 6, 111);
        ItemSold itemSoldFirst = new ItemSold(itemDTOFirst, 4);
        ItemDTO itemDTOSecond = new ItemDTO(10, "Banana", 6, 111);
        ItemSold itemSoldSecond = new ItemSold(itemDTOSecond, 1);

        saleInstance.registerItem(itemSoldFirst);
        saleInstance.registerItem(itemSoldSecond);

        assertFalse(saleInstance.getBoughtItems().contains(itemSoldSecond), "Item was added.");
        int expectedQuantity = 5;
        assertEquals(expectedQuantity, saleInstance.getBoughtItems().get(0).getQuantity(), "Wrong quantity.");
    }

    @Test
    void calculateChange() {
        int amountPaid = 100;
        int expectedChange = 100;
        saleInstance.calculateChange(amountPaid);
        assertEquals(expectedChange, saleInstance.getChange(), "Change is calculated incorrectly.");
    }
}