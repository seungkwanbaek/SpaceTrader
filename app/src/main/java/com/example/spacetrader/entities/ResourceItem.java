package com.example.spacetrader.entities;

public class ResourceItem {
    private String resourceName;
    private long resroucePrice;
    private long resourceAmount;

    /**
     * Constructor of ResourceItem
     * @param name the resource name
     * @param price the resource price
     * @param amount the resource amount
     */
    public ResourceItem(String name, long price, long amount) {
        this.resourceAmount = amount;
        this.resroucePrice = price;
        this.resourceName = name;
    }

    /**
     * Getter method for resource name
     * @return the resource name
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Getter method for resource price
     * @return the resource price
     */
    public long getResroucePrice() {
        return resroucePrice;
    }

    /**
     * Getter method for resource amount
     * @return the resource amount
     */
    public long getResrouceAmount() {
        return resourceAmount;
    }

    /**
     * Setter method for resource name
     * @param resourceName the resource name
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Setter method for resource price
     * @param resroucePrice the resource price
     */
    public void setResroucePrice(int resroucePrice) {
        this.resroucePrice = resroucePrice;
    }

    /**
     * Setter method for resource amount
     * @param amount the resource amount
     */
    public void setResourceAmount(int amount) {
        this.resourceAmount = amount;
    }
}
