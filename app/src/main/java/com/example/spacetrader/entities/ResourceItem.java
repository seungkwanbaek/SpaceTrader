package com.example.spacetrader.entities;

/**
 * The ResourceItem class
 */
public class ResourceItem {
    private String resourceName;
    private long resourcePrice;
    private long resourceAmount;

    /**
     * Constructor of ResourceItem
     * @param name the resource name
     * @param price the resource price
     * @param amount the resource amount
     */
    public ResourceItem(String name, long price, long amount) {
        this.resourceAmount = amount;
        this.resourcePrice = price;
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
    public long getResourcePrice() {
        return resourcePrice;
    }

    /**
     * Getter method for resource amount
     * @return the resource amount
     */
    public long getResourceAmount() {
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
     * @param resourcePrice the resource price
     */
    public void setResourcePrice(int resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    /**
     * Setter method for resource amount
     * @param amount the resource amount
     */
    public void setResourceAmount(int amount) {
        this.resourceAmount = amount;
    }
}
