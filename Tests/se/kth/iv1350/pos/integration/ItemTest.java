package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item firstItem;
    private Item secondItem;

    @BeforeEach
    void setUp() {
        firstItem = new Item("test",1,1,1,5);
        secondItem = new Item("test",1,1,1,4);
    }

    @AfterEach
    void tearDown() {
        firstItem = null;
        secondItem = null;
    }

    @Test
    void updateAmountPresent() {
        firstItem.updateAmountPresent(1);
        assertEquals(firstItem.getAmountPresent(), secondItem.getAmountPresent(), "Not matching.");
    }
}