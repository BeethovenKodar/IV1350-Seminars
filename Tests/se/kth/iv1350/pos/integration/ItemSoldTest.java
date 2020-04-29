package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemSoldTest {

    private ItemSold firstItem;

    @BeforeEach
    void setUp() {
        firstItem = new ItemSold(new ItemDTO(1,"test",1,1),4);
    }

    @AfterEach
    void tearDown() {
        firstItem = null;
    }

    @Test
    void updateQuantity() {
        firstItem.updateQuantity(1);
        int expectedQuantity = 5;
        assertEquals(expectedQuantity, firstItem.getQuantity(), "sold quantity not updating right.");
    }
}