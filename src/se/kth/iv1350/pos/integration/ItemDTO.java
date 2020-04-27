package se.kth.iv1350.pos.integration;

/**
 * This class represents an item that has been fetched from the inventory system.
 */
public class ItemDTO {

    private int itemPrice;
    private String itemName;
    private int itemVAT;
    private int barCode;

    public ItemDTO (int price, String name, int vat, int barCode) {
        this.itemPrice = price;
        this.itemName = name;
        this.itemVAT = vat;
        this.barCode = barCode;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public int getBarCode() {
        return barCode;
    }
}
