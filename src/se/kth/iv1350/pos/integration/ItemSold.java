package se.kth.iv1350.pos.integration;

/**
 * This class keeps track of the items that have been bought.
 */
public class ItemSold {

    private ItemDTO itemDTO;
    private int quantity;

    public ItemSold(ItemDTO item, int quantity) {
        this.itemDTO = item;
        this.quantity = quantity;
    }

    /**
     * Method used to update the quantity of a certain item that's bought.
     *
     * @param amount amount of entities that should be added.
     */
    public void updateQuantity(int amount) {
        this.quantity += amount;
    }

    public ItemDTO getItem() {
        return itemDTO;
    }

    public int getQuantity() {
        return quantity;
    }
}
