package org.dc.sc.pojos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Data
public class Item {

    private String itemType;
    private String itemName;
    private float itemDiscountPercentage;
    private String itemOfferType;
    private BigDecimal itemPrice;

}
