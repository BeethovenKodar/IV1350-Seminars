package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Reciept;

/**
 * This class is a collection of various external systems.
 */
public class ExternalSystemHandler {

    private SaleLog saleLog;
    private ItemInventory itemInv;
    private AccountingSystem acctSys;
    private Printer printer;

    public ExternalSystemHandler(SaleLog saleLog, ItemInventory itemInv, AccountingSystem acctSys, Printer printer) {
        this.saleLog = saleLog;
        this.itemInv = itemInv;
        this.acctSys = acctSys;
        this.printer = printer;
    }

    /**
     * This method hands out different parts of the information a
     * <code>SaleInfoDTO</code> contains to different systems.
     *
     * @param saleInfo The information to be distrubited.
     */
    public void handleExternalSystems(SaleInfoDTO saleInfo) {
        saleLog.addToSaleLog(saleInfo);
        itemInv.updateInventory(saleInfo.getSale().getBoughtItems());
        acctSys.updateAccounting(saleInfo.getSale().getRunningTotal());
        printReceipt(saleInfo);
    }

    private void printReceipt(SaleInfoDTO saleInfo) {
        Reciept reciept = new Reciept(saleInfo);
        String recieptToPrint = reciept.makeRecieptToString();
        printer.print(recieptToPrint);
    }

    public boolean checkValidity(int barCode) {
        return itemInv.checkValidity(barCode);
    }

    public ItemDTO getItem (int barCode) {
        return itemInv.getItem(barCode);
    }
}
