package com.example.spacetrader.entities;

public class Resource {
    String name;
    int MTLP;
    int MTLU;
    int TTP;
    int basePrice;
    int IPL;
    int var;
    String IE;
    String CR;
    String ER;
    int MTL;
    int MTH;

    /**
     * Constructor of Resource
     * @param name resource name
     * @param MTLP the MTLP value
     * @param MTLU the MTLU value
     * @param TTP the TTP value
     * @param basePrice the base price
     * @param IPL the IPL value
     * @param var the var
     * @param IE the IE value
     * @param CR the CR value
     * @param ER the ER value
     * @param MTL the MTL value
     * @param MTH the MTH value
     */
    public Resource(String name, int MTLP, int MTLU, int TTP, int basePrice, int IPL,
                    int var, String IE, String CR, String ER, int MTL, int MTH) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = var;
        this.IE = IE;
        this.CR = CR;
        this.ER = ER;
        this.MTL = MTL;
        this.MTH = MTH;
    }

    /**
     * Getter method of name
     * @return the resource name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Calculate price with techLevel
     * @param techLevelValue the techLevel
     * @return the calculated price
     */
    public int getPrice(int techLevelValue) {
        return (basePrice + IPL * (techLevelValue - MTLP) + var);
    }


}
