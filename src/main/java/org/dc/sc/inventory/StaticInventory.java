package org.dc.sc.inventory;

import jakarta.annotation.PostConstruct;
import org.dc.sc.enums.ItemName;
import org.dc.sc.enums.ItemOfferType;
import org.dc.sc.pojos.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StaticInventory {
    public static ConcurrentHashMap<String, List<Item>> inventoryMap =
            new ConcurrentHashMap<>();
    @PostConstruct
    public void setInventoryMap()
    {
        inventoryMap.put(ItemName.APPLE.name,getAppleList(50));
        inventoryMap.put(ItemName.BANANA.name,getBananaList(50));
        inventoryMap.put(ItemName.MELON.name,getMelonList(50));
        inventoryMap.put(ItemName.LEMON.name,getLemonList(50));
    }
    private List<Item> getAppleList(int count)
    {
        List<Item> appleList = new ArrayList<>();
        for(int i=0;i<count;i++)
        {
            appleList.add(Item.builder().itemType("fruit").itemName(ItemName.APPLE.name).
                    itemPrice(new BigDecimal("0.35")).itemDiscountPercentage(0).build());
        }
        return appleList;
    }
    private List<Item> getBananaList(int count)
    {
        List<Item> bananaList = new ArrayList<>();
        for(int i=0;i<count;i++)
        {
            bananaList.add(Item.builder().itemType("fruit").itemName(ItemName.BANANA.name).
                    itemPrice(new BigDecimal("0.20")).itemDiscountPercentage(0).build());
        }
        return bananaList;
    }
    private List<Item> getMelonList(int count)
    {
        List<Item> melonList = new ArrayList<>();
        for(int i=0;i<count;i++)
        {
            melonList.add(Item.builder().itemType("fruit").itemName(ItemName.MELON.name).
                    itemOfferType(ItemOfferType.BUYONEGETONE.name).
                    itemPrice(new BigDecimal("0.50")).itemDiscountPercentage(50).build());
        }
        return melonList;
    }
    private List<Item> getLemonList(int count)
    {
        List<Item> lemonList = new ArrayList<>();
        for(int i=0;i<count;i++)
        {
            lemonList.add(Item.builder().itemType("fruit").itemName(ItemName.LEMON.name).
                    itemOfferType(ItemOfferType.BUYTWOGETONE.name).
                    itemPrice(new BigDecimal("0.15")).itemDiscountPercentage(33.3f).build());
        }
        return lemonList;
    }
}
