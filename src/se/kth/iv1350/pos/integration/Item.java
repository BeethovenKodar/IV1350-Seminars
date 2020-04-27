package se.kth.iv1350.pos.integration;

/**
 * An instance of this class represents an item in the store. Only found in the item inventory.
 */
public class Item {

    private int itemPrice;
    private String itemName;
    private int percentsOfVAT;
    private int barCode;
    private int amountPresent;

    public Item(String name, int price, int vat, int barCode, int amountPresent) {
        this.itemPrice = price;
        this.itemName = name;
        this.percentsOfVAT = vat;
        this.barCode = barCode;
        this.amountPresent = amountPresent;
    }

    /**
     * A method that is called when an item already scanned, is scanned again.
     *
     * @param amountSold Amount of new items of same sort.
     */
    public void updateAmountPresent(int amountSold) {
        amountPresent -= amountSold;
    }

    public int getBarCode() {
        return barCode;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPercentsOfVAT() {
        return percentsOfVAT;
    }


}
