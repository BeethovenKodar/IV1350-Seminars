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
    private String date;
    private List<ItemSold> boughtItems = new ArrayList<ItemSold>();
    private int totalPrice;
    private int amountPaid;
    private int change;
    private int totalVAT = 12;

    public Reciept (SaleInfoDTO saleInfoDTO) {
        this.storeName = saleInfoDTO.getRetStore().getStoreName();
        this.storeAddress = saleInfoDTO.getRetStore().getStoreAddress();
        this.date = saleInfoDTO.getSale().getDate();
        this.boughtItems = saleInfoDTO.getSale().getBoughtItems();
        this.totalPrice = saleInfoDTO.getSale().getRunningTotal();
        this.amountPaid = saleInfoDTO.getSale().getAmountPaid();
        this.change = saleInfoDTO.getSale().getChange();
    }

    public String makeRecieptToString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("-----------------------");
        receipt.append("\n" + storeName + "\n" + storeAddress + "\n");
        receipt.append(date + "\n");
        for (ItemSold item : boughtItems) {
            receipt.append("\n" + item.getQuantity() + " " + item.getItem().getItemName() + " @ " +
                      item.getItem().getItemPrice() + " kr/each - " +
                      item.getQuantity()*item.getItem().getItemPrice() + " kr");
        }
        receipt.append("\n\n" + "Total price: " + totalPrice + "\n");
        receipt.append("Paid in cash: " + amountPaid + "\nChange: " + change);
        receipt.append("\n-----------------------");
        return receipt.toString();
    }
}
