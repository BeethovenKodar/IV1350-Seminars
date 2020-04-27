package se.kth.iv1350.pos.integration;

/**
 * This class represents an active discount that applies to an item in the store.
 */
public class DiscountDTO {

    private int barCode;
    private int amountOff;

    DiscountDTO (int barCode, int amountOff) {
        this.barCode = barCode;
        this.amountOff = amountOff;
    }

    public int getBarCodeLinkedToDiscount() {
        return barCode;
    }

    public int getAmountOff() {
        return amountOff;
    }
}
