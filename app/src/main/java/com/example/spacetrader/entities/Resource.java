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

    public String getName() {
        return this.name;
    }
    public int getMTLP() {
        return this.getMTLP();
    }

    public int getPrice(int techLevelValue) {
        return (basePrice + IPL * (techLevelValue - MTLP) + var);
    }


}
