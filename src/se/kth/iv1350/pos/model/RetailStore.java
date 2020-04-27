package se.kth.iv1350.pos.model;

/**
 * This class represents a retail store, containing information about it.
 */
public class RetailStore {

    private String storeName;
    private String storeAddress;

    public RetailStore(String name, String address) {
        this.storeName = name;
        this.storeAddress = address;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public String getStoreAddress() {
        return this.storeAddress;
    }
}
