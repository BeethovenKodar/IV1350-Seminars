package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the main class that handles necessary information regarding a sale instance.
 */
public class Sale {

    private ExternalSystemHandler extSysHan;
    private List<ItemSold> boughtItems = new ArrayList<>();
    private int runningTotal;
    private String date;
    private int amountPaid;
    private int change;
    private SaleObserver saleObserver;

    public Sale (ExternalSystemHandler extSysHan) {
        this.extSysHan = extSysHan;
    }

    public void addSaleObserver(SaleObserver saleObserver) {
        this.saleObserver = saleObserver;
    }

    /**
     * This method retrieves an item, and then return it.
     *
     * @param barCode The barcode that is connected to one item.
     * @param quantity Quantity bought of the given barcode.
     * @return A DisplayDTO that holds information about the scanned item.
     * @throws ItemInventoryException Thrown if there is an error when trying to fetch an item.
     * @throws ItemInventoryException Thrown if there is an error when trying to fetch an item.
     */
    public DisplayDTO scanItem (int barCode, int quantity) throws InvalidBarcodeException, ItemInventoryException {
        ItemDTO itemDTO = extSysHan.getItem(barCode);
        ItemSold itemSold = new ItemSold(itemDTO, quantity);
        int currentPrice = registerItem(itemSold);
        return new DisplayDTO(itemSold, currentPrice);
    }

    /**
     * This method adds the specified item to the list of bought items.
     *
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

    public SaleInfoDTO printReceipt(Printer printer, RetailStore retStore) {
        SaleInfoDTO saleInfo = createSaleInfoDTO(retStore);
        Reciept reciept = new Reciept(saleInfo);
        printer.print(reciept.makeRecieptToString());
        return saleInfo;
    }

    /**
     * A method called when a payment is ongoing.
     *
     * @param amountPaid The amount given by the customer.
     * @return The difference between the amount paid and the final price.
     */
    public void calculateChange(int amountPaid) {
        this.amountPaid = amountPaid;
        this.change = amountPaid - runningTotal;
        signalToListeners(runningTotal);
    }

    private void signalToListeners(int finalPrice) {
        saleObserver.paymentCompleted(finalPrice);
    }

    /**
     * This method creates a DTO containing information about a completed sale.
     *
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM @ hh:mm:ss");
        date = dateFormat.format(new Date());
    }

    public String getDate() {
        return this.date;
    }

    public int getRunningTotal() {
        return runningTotal;
    }

    public List<ItemSold> getBoughtItems() {
        return boughtItems;
    }

    public int getAmountPaid() {
        return this.amountPaid;
    }

    public int getChange() {
        return this.change;
    }
}
