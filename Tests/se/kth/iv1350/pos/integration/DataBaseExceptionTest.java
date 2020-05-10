package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseExceptionTest {

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
    void DBEShouldBeThrownTest() throws InvalidBarcodeException {
        int barCodeThatShouldGenerateError = 10;
        String expectedMessage = "The item inventory's data base could not be reached, SQL error.";
        try {
            invInstance.getItem(barCodeThatShouldGenerateError);
            fail("The barCode did not cause an exception.");
        } catch (DataBaseException DBE) {
            assertEquals(expectedMessage, DBE.getMessage(),
                         "Wrong message");
        }
    }

    @Test
    void DBEShouldNotBeThrownTest() throws InvalidBarcodeException {
        int barCodeThatShouldPass = 11;
        try {
            invInstance.getItem(barCodeThatShouldPass);
        } catch (DataBaseException DBE) {
            fail("The exception should not have been thrown.");
        }
    }
}