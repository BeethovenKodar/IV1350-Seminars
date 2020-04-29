package se.kth.iv1350.pos.startup;


import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.RetailStore;
import se.kth.iv1350.pos.view.View;

/**
 * This class initializes the entire application through the main method.
 */
public class Main {
    /**
     * The main method that initializes the entire application, creating necessary objects.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        AccountingSystem acctSys = new AccountingSystem();
        SaleLog saleLog = new SaleLog();
        RetailStore retStore = new RetailStore("KTH-Shop", "Isafjordsgatan 22");
        ItemInventory itemInv = new ItemInventory();
        Printer printer = new Printer();
        ExternalSystemHandler extSysHan = new ExternalSystemHandler(saleLog, itemInv, acctSys, printer);
        Controller contr = new Controller(extSysHan, retStore);
        View view = new View(contr);
        view.simulatePurchase();
    }
}
