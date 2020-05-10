package se.kth.iv1350.pos.integration;

/**
 * This exception is thrown when an item-identifier can't be found in the item inventory.
 */
public class InvalidBarcodeException extends Exception {

    private int barCode;

    public InvalidBarcodeException(int barCode) {
        super("There is no item with the barcode " + barCode + "");
        this.barCode = barCode;
    }

    public int getBarCode() {
        return barCode;
    }
}
