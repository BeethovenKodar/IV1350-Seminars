package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.RetailStore;
import se.kth.iv1350.pos.model.Sale;


/**
 * The class holds information about a finished purchase in the POS.
 */
public class SaleInfoDTO {

    private Sale sale;
    private RetailStore retStore;

    public SaleInfoDTO(Sale sale, RetailStore retStore) {
        this.sale = sale;
        this.retStore = retStore;
    }

    public Sale getSale() {
        return sale;
    }

    public RetailStore getRetStore() {
        return this.retStore;
    }
}
