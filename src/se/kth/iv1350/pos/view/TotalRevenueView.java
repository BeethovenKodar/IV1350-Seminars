package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.model.SaleObserver;

public class TotalRevenueView implements SaleObserver {

    private int amountSoldForSinceStart;

    public TotalRevenueView() {
        amountSoldForSinceStart = 0;
    }

    @Override
    public void paymentCompleted(int amountReceived) {
        amountSoldForSinceStart += amountReceived;
        System.out.println("TOTALREVENUEVIEW - Total Revenue: " + amountSoldForSinceStart + "kr <");
    }
}
