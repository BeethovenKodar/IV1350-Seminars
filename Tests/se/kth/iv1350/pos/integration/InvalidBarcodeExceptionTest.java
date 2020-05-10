package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class InvalidBarcodeExceptionTest {

    private ItemInventory invInstance;

    @BeforeEach
    void setUp() {
        invInstance = new ItemInventory();
    }

    @AfterEach
    void tearDown() {
        invInstance = null;
    }

    @Test
    void IBEShouldBeThrownTest() throws DataBaseException {
        int invalidBarCode = 9;
        try {
            invInstance.getItem(invalidBarCode);
            fail("Exception was not thrown when it should have been");
        } catch (InvalidBarcodeException IBE) {
            assertEquals(IBE.getBarCode(), invalidBarCode, "Exception message holds wrong barcode.");
        }
    }

    @Test
    void IBEShouldNotBeThrownTest() throws DataBaseException {
        int validBarCode = 13;
        try {
            invInstance.getItem(validBarCode);
        } catch (InvalidBarcodeException IBE){
            fail("The exception was thrown wrongfully.");
        }
    }
}