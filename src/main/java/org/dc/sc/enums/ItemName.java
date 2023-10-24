package org.dc.sc.enums;

public enum ItemName {
    APPLE("apple"),
    BANANA("banana"),
    MELON("melon"),
    LEMON("lemon");

    public final String name;

    private ItemName(String name) {
        this.name = name;
    }

}
