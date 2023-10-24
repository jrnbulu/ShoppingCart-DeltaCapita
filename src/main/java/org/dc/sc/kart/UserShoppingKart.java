package org.dc.sc.kart;

import lombok.Data;
import org.dc.sc.pojos.Item;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@Component
public class UserShoppingKart {
    private Map<String, List<Item>> kart = new HashMap<>();
}
