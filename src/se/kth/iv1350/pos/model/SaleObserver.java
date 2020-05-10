package se.kth.iv1350.pos.model;

/**
 * This is an interface. It is implemented by classes that are interested in getting a notification
 * when a payment has been completed.
 */
public interface SaleObserver {

    /**
     * This method is called when a payment has been made during the current sale.
     *
     * @param amountReceived The amount equivalent to all items sold.
     */
    void paymentCompleted(int amountReceived);
}
