package se.kth.iv1350.pos.integration;

/**
 * This exception is thrown when an error has been detected in the <code>ItemInventory</code>
 */
public class ItemInventoryException extends Exception {

    public ItemInventoryException(String message, Exception reason) {
        super(message, reason);
    }
}
