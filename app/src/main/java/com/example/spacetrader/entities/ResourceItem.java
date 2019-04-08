package com.example.spacetrader.entities;

public class ResourceItem {
    private String resourceName;
    private long resroucePrice;
    private long resourceAmount;

    public ResourceItem(String name, long price, long amount) {
        this.resourceAmount = amount;
        this.resroucePrice = price;
        this.resourceName = name;
    }

    public String getResourceName() {
        return resourceName;
    }

    public long getResroucePrice() {
        return resroucePrice;
    }

    public long getResrouceAmount() {
        return resourceAmount;
    }

    public void setResourceAmount(int amount) {
        this.resourceAmount = amount;
    }
}
