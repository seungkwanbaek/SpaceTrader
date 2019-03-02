package com.example.spacetrader.entities;

/**
 * Enumeration of every possible type of trading good that can be purchased
 * in the game.
 *
 * @author Bryan
 */

public enum TradeGood implements CargoItem {

    WATER(30),
    FURS(250),
    FOOD(100),
    ORE(350),
    GAMES(250),
    FIREARMS(1250),
    MEDICINE(650),
    MACHINES(900),
    NARCOTICS(3500),
    ROBOTS(5000);

    private final int basePrice;
    private TradeGood(int base) {
        basePrice = base;
    }

    @Override
    public String getItemName() {
        String s = this.toString();
        char c = s.charAt(0);
        return Character.toUpperCase(c) + s.substring(1).toLowerCase();
    }

    /**
     * @return the basePrice
     */
    public int getBasePrice() {
        return basePrice;
    }

}
