package org.dc.sc.enums;

public enum ItemOfferType {
    BUYONEGETONE("b1g1"),
    BUYTWOGETONE("b2g1");

    public final String name;

    private ItemOfferType(String name) {
        this.name = name;
    }
}
