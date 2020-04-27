package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemSold;
import se.kth.iv1350.pos.integration.SaleInfoDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represents a proof of purchase with suiting details about it.
 */
public class Reciept {

    private String storeName;
    private String storeAddress;
    private Date date;
    private List<ItemSold> boughtItems = new ArrayList<ItemSold>();
    private int totalPrice;
    private int amountPaid;
    private int change;
    private int totalVAT = 12;
    private String customerID;

    public Reciept (SaleInfoDTO saleInfoDTO) {
        this.storeName = saleInfoDTO.getRetStore().getStoreName();
        this.storeAddress = saleInfoDTO.getRetStore().getStoreAddress();
        this.date = saleInfoDTO.getSale().getDate();
        this.boughtItems = saleInfoDTO.getSale().getBoughtItems();
        this.totalPrice = saleInfoDTO.getSale().getRunningTotal();
        this.amountPaid = saleInfoDTO.getSale().getAmountPaid();
        this.change = saleInfoDTO.getSale().getChange();
        this.customerID = saleInfoDTO.getSale().getCustomerID();
    }

    public String makeRecieptToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------");
        sb.append("\n" + storeName + "\n" + storeAddress + "\n");
        sb.append(date + "\n");
        for (ItemSold item : boughtItems) {
            sb.append("\n" + item.getQuantity() + " " + item.getItem().getItemName() + " @ " +
                      item.getItem().getItemPrice() + " kr/each - " +
                      item.getQuantity()*item.getItem().getItemPrice() + " kr");
        }
        sb.append("\n\n" + "Total price: " + totalPrice + "\n");
        sb.append("Paid in cash: " + amountPaid + "\nChange: " + change);
        sb.append("\n-----------------------");
        return sb.toString();
    }
}
