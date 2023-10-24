package org.dc.sc.service;

import lombok.RequiredArgsConstructor;
import org.dc.sc.enums.ItemOfferType;
import org.dc.sc.kart.UserShoppingKart;
import org.dc.sc.pojos.Item;
import org.springframework.stereotype.Service;
import org.dc.sc.inventory.StaticInventory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingKartServce {

    private final UserShoppingKart userShoppingKart;

    public Map<String,List<Item>> getAllItems()
    {
        return StaticInventory.inventoryMap;
    }
    public List<Item> getItemsByName(String itemName)
    {
        return StaticInventory.inventoryMap.get(itemName);
    }
    public Map<String,List<Item>> getKartItems()
    {
        return userShoppingKart.getKart();
    }
    public void addToKart(Item item)
    {
        List<Item> items = userShoppingKart.getKart().get(item.getItemName());
        if(null == items) {
            items = new ArrayList<>();
            userShoppingKart.getKart().put(item.getItemName(),items);
        }
        StaticInventory.inventoryMap.get(item.getItemName()).remove(item);
        userShoppingKart.getKart().get(item.getItemName()).add(item);
    }
    public void removeFromKart(Item item)
    {
        userShoppingKart.getKart().get(item.getItemName()).remove(item);
        StaticInventory.inventoryMap.get(item.getItemName()).add(item);
    }
    public BigDecimal getKartValue()
    {
        BigDecimal totalPrice = new BigDecimal(0);
        MathContext mc = new MathContext(2);
        for (Map.Entry<String, List<Item>> entry :
                userShoppingKart.getKart().entrySet()) {
            if(null != entry.getValue() && !entry.getValue().isEmpty() &&
                    !entry.getValue().get(0).getItemOfferType().isBlank())
            {
                totalPrice = totalPrice.add(getPriceByOffer(entry.getKey(),entry.getValue()),mc);
            }
            else {
                for(Item item :  entry.getValue())
                {
                    totalPrice = totalPrice.add(item.getItemPrice().multiply(
                                    new BigDecimal(item.getItemDiscountPercentage()/100),mc),mc);
                }
            }

        }
        return totalPrice;
    }

    private BigDecimal getPriceByOffer(String key, List<Item> itemList) {
        BigDecimal totalPriceAfterOffer = new BigDecimal(0);
        MathContext mc = new MathContext(2);
        for(Item item:itemList)
        {
            totalPriceAfterOffer = totalPriceAfterOffer.add(item.getItemPrice(),mc);
        }
        if(itemList.get(0).getItemOfferType().equals(ItemOfferType.BUYONEGETONE.name))
        {
            totalPriceAfterOffer = totalPriceAfterOffer.subtract(itemList.get(0).getItemPrice().multiply(new BigDecimal(itemList.size()/2),mc),mc);
        }
        if(itemList.get(0).getItemOfferType().equals(ItemOfferType.BUYTWOGETONE.name))
        {
            totalPriceAfterOffer = totalPriceAfterOffer.subtract(itemList.get(0).getItemPrice().multiply(new BigDecimal(itemList.size()/3),mc),mc);
        }
        return totalPriceAfterOffer;
    }
}