package com.example.spacetrader.entities;

public class ResourceItem {
    private String resourceName;
    private int resroucePrice;
    private int resourceAmount;

    public ResourceItem(String name, int price, int amount) {
        this.resourceAmount = amount;
        this.resroucePrice = price;
        this.resourceName = name;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getResroucePrice() {
        return resroucePrice;
    }

    public int getResrouceAmount() {
        return resourceAmount;
    }

    public void setResourceAmount(int amount) {
        this.resourceAmount = amount;
    }
}
