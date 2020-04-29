package se.kth.iv1350.pos.integration;

/**
 * This class keeps track of the balance for a retail store.
 */
public class AccountingSystem {

    int currentBalance;

    public AccountingSystem() {
        currentBalance = 50;
    }

    /**
     * A method updating the accounting system.
     *
     * @param amountReceived The amount received from selling a number of items in a sale.
     */
    void updateAccounting (int amountReceived) {
        currentBalance += amountReceived;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }
}
