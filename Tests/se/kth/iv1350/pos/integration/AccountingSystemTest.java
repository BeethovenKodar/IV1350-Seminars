package se.kth.iv1350.pos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountingSystemTest {

    private int amountToAdd;
    private AccountingSystem acctSys;

    @BeforeEach
    void setUp() {
        amountToAdd = 25;
        acctSys = new AccountingSystem();
    }

    @AfterEach
    void tearDown() {
        amountToAdd = 0;
        acctSys = null;
    }

    @Test
    void updateAccountingTest() {
        int expected = 75;
        acctSys.updateAccounting(amountToAdd);
        assertEquals(expected, acctSys.getCurrentBalance(), "Balance not updating correctly.");
    }
}