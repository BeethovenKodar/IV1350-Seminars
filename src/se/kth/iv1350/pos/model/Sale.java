package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemSold;
import se.kth.iv1350.pos.integration.SaleInfoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the main class that handles necessary information regarding a sale instance.
 */
public class Sale {

    private List<ItemSold> boughtItems = new ArrayList<ItemSold>();
    private int runningTotal;
    private String customerID = "000";
    private Date date;
    private int amountPaid;
    private int change;

    /**
     * This method adds the specified item to the list of bought items, also
     * @param itemSold The item to be added to the sale
     * @return The updated total after the passed item has been registered.
     */
    public int registerItem (ItemSold itemSold) {
        if(!(checkForDuplicate(itemSold)))
            boughtItems.add(itemSold);
        updateTotal((itemSold.getItem()).getItemPrice(), itemSold.getQuantity());
        return runningTotal;
    }

    private boolean checkForDuplicate(ItemSold itemSold) {
        for (ItemSold item : boughtItems) {
            if (itemSold.getItem().getItemName().equals(item.getItem().getItemName())) {
                item.updateQuantity(itemSold.getQuantity());
                return true;
            }
        }
        return false;
    }

    /**
     * Method that handles the updating of the final price after discounts.
     *
     * @param discountAmount The amount to be drawn off the runningTotal.
     * @param customerID The customer issuing the discount request.
     * @return The updated runningTotal, after discounts.
     */
    public int updateWithDiscount(int discountAmount, int customerID) {
        this.customerID = String.valueOf(customerID);
        updateTotal(discountAmount, 1);
        return runningTotal;
    }

    /**
     * A method called when a payment is ongoing.
     *
     * @param amountPaid The amount given by the customer.
     * @return The difference between the amount paid and the final price.
     */
    public int calculateChange(int amountPaid) {
        this.amountPaid = amountPaid;
        this.change = amountPaid - runningTotal;
        return this.change;
    }

    /**
     * This method creates a DTO containing information about a completed sale.
     * @param retStore Needed to complete the sale information.
     * @return A DTO with complete sale information.
     */
    public SaleInfoDTO createSaleInfoDTO(RetailStore retStore) {
        setDate();
        return new SaleInfoDTO(this, retStore);
    }

    private void updateTotal (int amount, int quantity) {
        runningTotal += (amount*quantity);
    }

    private void setDate() {
        date = new Date();
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public List<ItemSold> getBoughtItems() {
        return boughtItems;
    }

    public Date getDate() {
        return this.date;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public int getAmountPaid() {
        return this.amountPaid;
    }

    public int getChange() {
        return this.change;
    }
}
