package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.DisplayDTO;

import javax.swing.*;

/**
 * This class represents the real view and contains lines of code that will simulate an actual sale process.
 */
public class View {

    private Controller contr;

    /**
     * Creates a new instance of view, with a reference to a controller that will be used for all calls.
     *
     * @param contr The instance of controller that handles all calls.
     */
    public View (Controller contr) {
        this.contr = contr;
    }

    /**
     * This method will call all methods surrounding a faked sale process.
     */
    public void simulatePurchase() {
        contr.startNewSale();
        System.out.println("\nSale started, scan items\n");
        showOnScreen(contr.scanItem(13,1));
        showOnScreen(contr.scanItem(10,2));
        showOnScreen(contr.scanItem(10,2));
        showOnScreen(contr.scanItem(14,3));
        showOnScreen(contr.scanItem(14,3));
        showOnScreen(contr.scanItem(13,1));
        showOnScreen(contr.endSale());
        showPriceAfterDiscountOnScreen(contr.signalDiscountRequest(121));
        showChangeOnScreen(contr.pay(600));
        contr.handleExternalSystems();
    }

    private void showOnScreen (DisplayDTO printOut) {
        if (printOut.getMessage() != null) {
            System.out.println(printOut.getMessage());
        } else if (printOut.getItemSold() != null) {
            System.out.println(printOut.getItemSold().getQuantity() +
                               " pcs. " + printOut.getItemSold().getItem().getItemName() +
                               " @ " + printOut.getItemSold().getItem().getItemPrice() +
                               "kr/each " + " - Running Total: " + printOut.getRunningTotal());
        } else {
            System.out.println("------------------------\n" +
                               "Final price: " + printOut.getRunningTotal() + ", VAT included.");
        }
    }

    private void showPriceAfterDiscountOnScreen(DisplayDTO finalPrice) {
        System.out.println("Price after used discounts: " + finalPrice.getRunningTotal() + ", VAT included.");
    }

    private void showChangeOnScreen(DisplayDTO printOut) {
        System.out.println("Printing reciept...\n");
    }
}
