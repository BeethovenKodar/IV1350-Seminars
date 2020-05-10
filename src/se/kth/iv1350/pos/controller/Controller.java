package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.RetailStore;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.SaleObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {

    private Sale sale;
    private RetailStore retStore;
    private ExternalSystemHandler extSysHan;
    private Printer printer;
    private SaleObserver saleObserver;

    public Controller(ExternalSystemHandler extSysHan, RetailStore retStore, Printer printer) {
        this.extSysHan = extSysHan;
        this.retStore = retStore;
        this.printer = printer;
    }

    public void addSaleObserver(SaleObserver saleObserver) {
        this.saleObserver = saleObserver;
    }

    /**
     * This method simply starts a new sale instance.
     */
    public void startNewSale() {
        sale = new Sale(extSysHan);
        sale.addSaleObserver(saleObserver);
    }

    /**
     * In this method a given item is validated and then sent to the view, or give an error message.
     *
     * @param barCode The identifier for the item currently being scanned and processed.
     * @param quantity The amount of items to be scanned of the same type (same barcode).
     * @return A <code>DisplayDTO</code> that will contain information about the scanned item
     * that will be displayed in the view.
     * @throws InvalidBarcodeException Thrown when the called method is given an invalid barcode.
     * @throws ItemInventoryException Thrown when the called method cannot further communicate
     * with the <code>ItemInventory</code>
     */
    public DisplayDTO scanItem (int barCode, int quantity) throws InvalidBarcodeException, ItemInventoryException {
        return sale.scanItem(barCode, quantity);
    }

    /**
     * Method issued when no more items are to be scanned, presents the total price.
     * @return A <code>DisplayDTO</code> holding information for the price to be paid.
     */
    public DisplayDTO endSale() {
        return new DisplayDTO(sale.getRunningTotal());
    }

    /**
     * This method handles the payment.
     *
     * @param amountPaid The amount that the cashier has received from the customer.
     * @return A DisplayDTO containing the value for change.
     */
    public void pay(int amountPaid) {
        sale.calculateChange(amountPaid);
        SaleInfoDTO saleInfo = sale.printReceipt(printer, retStore);
        handleExternalSystems(saleInfo);
    }

    /**
     * When the sale has ended this method handles certain external systems of interest.
     * The sale log, accounting system, item inventory and the printer get affected.
     */
    public void handleExternalSystems(SaleInfoDTO saleInfo) {
        extSysHan.handleExternalSystems(saleInfo);
    }
}
