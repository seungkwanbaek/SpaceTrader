package com.example.spacetrader.entities;

import java.io.Serializable;
import java.util.HashMap;
import com.example.spacetrader.entities.TradeGood;


/**
 * Model of a Cargo Hold.
 *
 * @author Bryan
 */
public class CargoHold implements Serializable {
    private int capacity;
    private HashMap<CargoItem, Integer> cargo;

    /**
     * Create a new cargo hold.
     *
     * @param maxCapacity The capacity of the cargo hold.
     */
    public CargoHold(int maxCapacity) {
        capacity = maxCapacity;
        cargo = new HashMap<>();
        for (TradeGood good : TradeGood.values()) {
            cargo.put(good, 0);
        }
    }

    /**
     * Add a new item to the cargo hold.
     *
     * @param item The item to add to the cargo hold
     */
    public void addItem(CargoItem item) {
        if(getQuantity() < capacity) {
            boolean isNew = !cargo.keySet().contains(item);
            if(isNew) {
                cargo.put(item, 1);
            } else {
                cargo.put(item, cargo.get(item) + 1);
            }
        }
    }

    /**
     * @return The quantity of all items in the CargoHold.
     */
    public int getQuantity() {
        int size = 0;
        for(CargoItem item : cargo.keySet()) {
            size += getQuantity(item);
        }
        return size;
    }

    /**
     * @return The capacity of the CargoHold.
     */
    public int getCapacity() {
        return capacity;
    }

    public void increaseCapacity(int x) {
        capacity += x;
    }

    public void decreaseCapacity(int x) {
        capacity -= x;
    }

    /**
     * Add many of the same item to the cargo hold at once.
     *
     * @param item   The item to be added
     * @param amount The quantity of the item to be added
     */
    public void addItemQuantity(CargoItem item, int amount) {
        boolean isNew = !cargo.keySet().contains(item);
        if(isNew) {
            cargo.put(item, amount);
        } else {
            cargo.put(item, cargo.get(item) + amount);
        }
    }

    /**
     * Remove an item from the cargo hold.
     *
     * @param item The item to be removed
     * @return whether or not the item was successfully removed
     */
    public boolean removeItem(CargoItem item) {
        if(cargo.keySet().contains(item)) {
            if(cargo.get(item) == 1) {
                cargo.remove(item);
            } else {
                cargo.put(item, cargo.get(item) - 1);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get an array of every trade good contained in a cargo hold.
     *
     * @return The trade goods currently held in a cargo hold
     */
    public CargoItem[] getCargoItems() {
        CargoItem[] goods = new CargoItem[10];
        int count = 0;
        for(CargoItem item : cargo.keySet()) {
            goods[count] = item;
            count++;
        }
        CargoItem[] finalGoods = new CargoItem[count];
        System.arraycopy(goods, 0, finalGoods, 0, count);
        return finalGoods;
    }

    /**
     * Get the quantity of a particular item currently held in cargo.
     *
     * @param item The item being queried
     * @return The quantity of the item currently held
     */
    public int getQuantity(CargoItem item) {
        if(cargo.containsKey(item)) {
            return cargo.get(item);
        } else {
            return 0;
        }
    }

    /**
     * @return Whether or not the cargo hold has space for more items
     */
    public boolean hasSpace() {
        return getQuantity() < capacity;
    }

    /**
     * @return The quantity of cargo currently held.
     */
    public int getCargoQuantity() {
        return cargo.size();
    }

    /**
     * @return remaining cargo space
     */
    public int getRemaining() {
        return capacity - cargo.size();
    }

    public HashMap<CargoItem, Integer> getCargo() {
        return cargo;
    }

}
