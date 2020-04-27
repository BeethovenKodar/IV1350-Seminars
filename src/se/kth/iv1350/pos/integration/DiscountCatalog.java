package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * The discount catalog holds information about discounts that can apply to certain items in the retail store.
 */
public class DiscountCatalog {

    private List<DiscountDTO> discounts = new ArrayList<DiscountDTO>();

    /**
     * The constructor adds hardcoded discounts to the catalog.
     */
    public DiscountCatalog() {
        setDiscounts();
    }

    private void setDiscounts() {
        discounts.add(new DiscountDTO(12, 5));
        discounts.add(new DiscountDTO(13, 40));
        discounts.add(new DiscountDTO(15, 8));
    }

    /**
     * This method checks for different types of discounts that can apply.
     *
     * @param sale Used to get a reference to the bought items list.
     * @param customerID Used for a potential
     * @return <code>discountAmount</code>, a negative value that should be "drawn off" the total.
     */
    public int checkForDiscount(Sale sale, int customerID) {
        int discountAmount = 0;
        discountAmount -= discountOnItem(sale);
        discountAmount -= discountBasedOnTotalPrice(sale);
        discountAmount -= discountForSpecificCustomers(sale, customerID);
        return discountAmount;
    }

    private int discountOnItem(Sale sale) {
        int itemDiscountTotal = 0;
        for (int i = 0; i < sale.getBoughtItems().size(); i++) {
            for (int j = 0; j < discounts.size(); j++) {
                if (sale.getBoughtItems().get(i).getItem().getBarCode() ==
                        discounts.get(j).getBarCodeLinkedToDiscount()) {
                    itemDiscountTotal += (discounts.get(j).getAmountOff() * sale.getBoughtItems().get(i).getQuantity());
                }
            }
        }
        return itemDiscountTotal;
    }

    private int discountBasedOnTotalPrice(Sale sale) {
        if (sale.getRunningTotal() >= 100)
            return 10;
        return 0;
    }

    private int discountForSpecificCustomers(Sale sale, int customerID) {
        if (customerID % 10 == 0)
            return (sale.getRunningTotal())/10;
        return 0;
    }
}
