package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.DisplayDTO;
import se.kth.iv1350.pos.integration.InvalidBarcodeException;
import se.kth.iv1350.pos.integration.ItemInventoryException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the real view and contains lines of code that will simulate an actual sale process.
 */
public class View {

    private Controller contr;
    private String exceptionLog = "\nThe following errors have been logged:";

    /**
     * Creates a new instance of view, with a reference to a controller that will be used for all calls.
     *
     * @param contr The instance of controller that handles all calls.
     */
    public View (Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
    }

    /**
     * This method will call all methods surrounding a faked sale process.
     */
    public void simulatePurchase() {
        contr.startNewSale();
        System.out.println("\nSale started, scan items\n");

        tryCatch(14,1);
        tryCatch(12,2);
        tryCatch(16,1);
        tryCatch(10,3);
        tryCatch(11,5);

        showOnScreen(contr.endSale());
        System.out.println("\nPRINTING RECEIPT...");
        contr.pay(150);
        System.out.println(exceptionLog);


        exceptionLog = "\nThe following errors have been logged:";
        contr.startNewSale();
        System.out.println("\n\n##########################\n\nSale started, scan items\n");

        tryCatch(11,1);
        tryCatch(12,2);
        tryCatch(17,1);
        tryCatch(12,3);
        tryCatch(10,5);

        showOnScreen(contr.endSale());
        System.out.println("\nPRINTING RECEIPT...");
        contr.pay(150);
        System.out.println(exceptionLog);
    }

    private void tryCatch(int barCode, int quantity) {
        try {
            showOnScreen(contr.scanItem(barCode, quantity));
        } catch (InvalidBarcodeException IBE) {
            handleException("The barcode given -> " + IBE.getBarCode() + ", is not valid", IBE);
        } catch (ItemInventoryException IIE) {
            handleException("Could not scan item", IIE);
        }
    }

    private void handleException(String message, Exception cause) {
        System.out.println("VIEW - [" + createDate() + "] - Error detected: " + message);
        addToDevLog(cause);
    }

    private void addToDevLog(Exception exception) {
        if (exception.getCause() != null)
            exceptionLog += "\nLOG - [" + createDate() + "] - " + exception.getCause().getMessage();
        else
            exceptionLog += "\nLOG - [" + createDate() + "] - " + exception.getMessage();
    }

    private String createDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM @ hh:mm:ss");
        return dateFormat.format(date);
    }

    private void showOnScreen (DisplayDTO printOut) {
        if (printOut.getItemSold() != null) {
            System.out.println(printOut.getItemSold().getQuantity() +
                               " pcs. " + printOut.getItemSold().getItem().getItemName() +
                               " @ " + printOut.getItemSold().getItem().getItemPrice() +
                               "kr/each " + " - Running Total: " + printOut.getRunningTotal());
        } else {
            System.out.println("------------------------\n" +
                               "TOTAL PRICE: " + printOut.getRunningTotal() + "kr");
        }
    }
}
