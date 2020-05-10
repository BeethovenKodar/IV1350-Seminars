package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.pos.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ItemInventoryExceptionTest {

    private ExternalSystemHandler extInstance;
    private ItemInventory invInstance;
    private Sale sale;

    @BeforeEach
    void setUp() {
        invInstance = new ItemInventory();
        extInstance = new ExternalSystemHandler(new SaleLog(), invInstance, new AccountingSystem());
        sale = new Sale(extInstance);
    }

    @AfterEach
    void tearDown() {
        invInstance = null;
        extInstance = null;
        sale = null;
    }

    @Test
    void IIEShouldBeThrownTest() throws InvalidBarcodeException {
        int barCodeThatThrowsDataBaseException = 10;
        String expectedMessage = "Call to item inventory unsuccessful.";
        try {
            extInstance.getItem(barCodeThatThrowsDataBaseException);
            fail("The exception was not thrown.");
        } catch (ItemInventoryException IIE) {
            assertEquals(IIE.getMessage(), expectedMessage, "Wrong message.");
        }
    }

    @Test
    void IIEShouldNotBeThrownTest() throws InvalidBarcodeException {
        int barCodeThatDoesNotThrowDataBaseException = 11;
        try {
            extInstance.getItem(barCodeThatDoesNotThrowDataBaseException);
        } catch (ItemInventoryException IIE) {
            fail("The exception was thrown, when it should not have been.");
        }
    }
}