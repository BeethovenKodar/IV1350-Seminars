package se.kth.iv1350.pos.integration;

/**
 * This class is a collection of various external systems.
 */
public class ExternalSystemHandler {

    private SaleLog saleLog;
    private ItemInventory itemInv;
    private AccountingSystem acctSys;

    public ExternalSystemHandler(SaleLog saleLog, ItemInventory itemInv, AccountingSystem acctSys) {
        this.saleLog = saleLog;
        this.itemInv = itemInv;
        this.acctSys = acctSys;
    }

    /**
     * This method hands out different parts of the information a
     * <code>SaleInfoDTO</code> contains to different systems.
     *
     * @param saleInfo The information to be distributed.
     */
    public void handleExternalSystems(SaleInfoDTO saleInfo) {
        saleLog.addToSaleLog(saleInfo);
        itemInv.updateInventory(saleInfo.getSale().getBoughtItems());
        acctSys.updateAccounting(saleInfo.getSale().getRunningTotal());
    }

    /**
     * Middleman class for handling the fetching of items.
     *
     * @param barCode The barcode that is searched for.
     * @return Returns the item connected to the specified barcode.
     * @throws InvalidBarcodeException Just passes by if it is thrown in lower layers.
     * @throws ItemInventoryException Thrown to generalize the DataBaseException if it is thrown in a lower layer.
     */
    public ItemDTO getItem(int barCode) throws InvalidBarcodeException, ItemInventoryException {
        try {
            return itemInv.getItem(barCode);
        } catch (DataBaseException DBE) {
            throw new ItemInventoryException("Call to item inventory unsuccessful.", DBE);
        }
    }
}
