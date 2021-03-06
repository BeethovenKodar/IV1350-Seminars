package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemInventoryTest {

    private ItemInventory instanceToTest;

    @BeforeEach
    void setUp() {
        instanceToTest = new ItemInventory();
    }

    @AfterEach
    void tearDown() {
        instanceToTest = null;
    }


    /*
    @Disabled
    @Test
    void checkValidityFound() {
        int barCode = 10;
        assertTrue(true == instanceToTest.checkValidity(barCode), "validation not functioning.");
    }*/

    /*
    @Disabled
    @Test
    void checkValidityNotFound() {
        int barCode = 9;
        assertTrue(false == instanceToTest.checkValidity(barCode), "validation not functioning.");
    }*/

    @Test
    void getItem() {
        String expectedItemName = "Banana";
        int barCodeForBanana = 10;
        try{
            assertEquals(expectedItemName, instanceToTest.getItem(barCodeForBanana).getItemName());
        } catch (InvalidBarcodeException | DataBaseException e){

        }

    }

    @Test
    void updateInventory() {
    }
}