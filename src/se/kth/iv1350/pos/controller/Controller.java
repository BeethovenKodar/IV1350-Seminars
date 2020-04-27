package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.RetailStore;
import se.kth.iv1350.pos.model.Sale;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {

    private Sale sale;
    private DiscountCatalog discCat;
    private RetailStore retStore;
    private ExternalSystemHandler extSysHan;

    public Controller(ExternalSystemHandler extSysHan, DiscountCatalog discCat, RetailStore retStore) {
        this.extSysHan = extSysHan;
        this.discCat = discCat;
        this.retStore = retStore;
    }

    /**
     * This method simply starts a new sale instance.
     */
    public void startNewSale() {
        sale = new Sale();
    }

    /**
     * In this method a given item is validated and then sent to the view, or give an error message.
     *
     * @param barCode The identifier for the item currently being scanned and processed.
     * @param quantity The amount of items to be scanned of the same type (same barcode).
     * @return A <code>DisplayDTO</code> that will contain information about the scanned item that will be displayed in the view.
     */
    public DisplayDTO scanItem (int barCode, int quantity) {
        if (extSysHan.checkValidity(barCode)) {
            ItemDTO itemDTO = extSysHan.getItem(barCode);
            ItemSold itemSold = new ItemSold(itemDTO, quantity);
            int currentPrice = sale.registerItem(itemSold);
            return new DisplayDTO(itemSold, currentPrice);
        } else {
            return new DisplayDTO("Item not found.");
        }
    }

    /**
     * Method issued when no more items are to be scanned, presents the total price.
     * @return A <code>DisplayDTO</code> holding information for the price to be paid.
     */
    public DisplayDTO endSale() {
        return new DisplayDTO(sale.getRunningTotal());
    }

    /**
     * If the customer wants a discount this method is executed.
     * @param customerID The customer's ID in the retail store's database.
     * @return The updated price to be displayed in the view.
     */
    public DisplayDTO signalDiscountRequest(int customerID) {
        int discountAmount = discCat.checkForDiscount(sale, customerID);
        int updatedRunningTotal = sale.updateWithDiscount(discountAmount, customerID);
        return new DisplayDTO(updatedRunningTotal);
    }

    /**
     * This method handles the payment.
     *
     * @param amountPaid The amount that the cashier has received from the customer.
     * @return A DisplayDTO containing the value for change.
     */
    public DisplayDTO pay(int amountPaid) {
        return new DisplayDTO(sale.calculateChange(amountPaid));
    }

    /**
     * When the sale has ended this method handles certain external systems of interest.
     * The sale log, accounting system, item inventory and the printer get affected.
     */
    public void handleExternalSystems() {
        extSysHan.handleExternalSystems(sale.createSaleInfoDTO(retStore));
    }
}
