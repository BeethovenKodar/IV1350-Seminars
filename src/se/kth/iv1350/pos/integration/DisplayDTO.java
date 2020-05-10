package se.kth.iv1350.pos.integration;

/**
 * This class is the object that's always returned to the view when something should be displayed,
 * and has multiple constructors for different uses.
 */
public class DisplayDTO {

    private ItemSold itemSold;
    private int amount;
    String message;

    /**
     * A constructor used when handling scanned items.
     *
     * @param itemSoldDTO Information about the scanned item.
     * @param runningTotal The running total after the latest item has been scanned.
     */
    public DisplayDTO (ItemSold itemSoldDTO, int runningTotal) {
        this.itemSold = itemSoldDTO;
        this.amount = runningTotal;
        this.message = null;
    }

    /**
     * A contructor using when only one piece of information as an integer should be displayed.
     *
     * @param amount The amount to be displayed.
     */
    public DisplayDTO (int amount) {
        this.itemSold = null;
        this.amount = amount;
        this.message = null;
    }

    public String getMessage() {
        return message;
    }

    public ItemSold getItemSold() {
        return itemSold;
    }

    public int getRunningTotal() {
        return amount;
    }
}
