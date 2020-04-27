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
     * @param amountRecieved The amount received from selling a number of items in a sale.
     */
    public void updateAccounting (int amountRecieved) {
        currentBalance += amountRecieved;
    }
}
