package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * The sale log holds information about finished purchases in the POS.
 */
public class SaleLog {

    private List <SaleInfoDTO> finishedSales = new ArrayList<SaleInfoDTO>();

    /**
     * This method adds a finished sale to the array of <code>finishedSales</code>.
     * @param saleInfo The finished sale to add.
     */
    void addToSaleLog(SaleInfoDTO saleInfo) {
        finishedSales.add(saleInfo);
    }
}
