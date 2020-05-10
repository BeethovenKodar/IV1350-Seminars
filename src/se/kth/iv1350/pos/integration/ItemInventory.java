package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * The item inventory holds information about items that can be found in the store.
 */
public class ItemInventory {

    private List<Item> inventory = new ArrayList<Item>();

    /**
     * Method is used to add items to the inventory.
     */
    public ItemInventory() {
        setInventory();
    }

    private void setInventory() {
        inventory.add(new Item("Banana", 5, 6, 10, 10));
        inventory.add(new Item("Apple", 4, 6, 11, 9));
        inventory.add(new Item("Large Milk", 19, 6, 12,5));
        inventory.add(new Item("Salmon", 120, 6, 13,3));
        inventory.add(new Item("Cigarettes", 45, 20, 14,20));
        inventory.add(new Item("Candy", 26, 6, 15,13));
    }


    /**
     * This method searches for a matching barcode in the inventory.
     *
     * @param barCode The barcode that is searched for.
     * @return <code>true</code> if it was found, <code>false</code> if not found.
     */
    /*public boolean checkValidity (int barCode) {
        for (Item item : inventory) {
            if (item.getBarCode() == barCode)
                return true;
        }
        return false;
    )*/

    /**
     * Fetches the validated item's information from the inventory.
     *
     * @param barCode The barcode connected to an item that should be fetched.
     * @return <code>ItemDTO</code> is an object containing information about the scanned item
     * @throws InvalidBarcodeException Thrown when a barcode does not exist.
     * @throws DataBaseException Should be interpreted as a connection problem between the
     * program and an external data base.
     */
    public ItemDTO getItem (int barCode) throws InvalidBarcodeException, DataBaseException {
        if (barCode == 10)
            throw new DataBaseException("The item inventory's data base could not be reached, SQL error.");

        int i = 0;
        while (inventory.get(i).getBarCode() != barCode) {
            i++;
            if(i >= inventory.size())
                throw new InvalidBarcodeException(barCode);
        }
        Item item = inventory.get(i);
        return new ItemDTO (item.getItemPrice(), item.getItemName(), item.getPercentsOfVAT(), item.getBarCode());
    }

    /**
     * This method is called to update the inventory after a sale is completed,
     * updating quantities of the items bought.
     *
     * @param soldItems The items and with corresponding quantities.
     */
    void updateInventory(List<ItemSold> soldItems) {
        for (ItemSold soldItem : soldItems) {
            for (Item invItem : inventory) {
                if (soldItem.getItem().getBarCode() == invItem.getBarCode())
                    invItem.updateAmountPresent(soldItem.getQuantity());
            }
        }
    }
}
