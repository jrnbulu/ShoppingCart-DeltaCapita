package org.dc.sc.controller;

import lombok.RequiredArgsConstructor;
import org.dc.sc.pojos.Item;
import org.dc.sc.service.ShoppingKartServce;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shoppingkart")
public class ShoppingKartController {
    private final ShoppingKartServce shoppingKartServce;
    @GetMapping("/allitems")
    public ResponseEntity<Map<String, List<Item>>> getAllItems()
    {
        return ResponseEntity.ok(shoppingKartServce.getAllItems());
    }
    @GetMapping("/item/{itemName}")
    public ResponseEntity <List<Item>> getItems(@PathVariable String itemName)
    {
        return ResponseEntity.ok(shoppingKartServce.getItemsByName(itemName));
    }
    @GetMapping("/kartitems")
    public ResponseEntity<Map<String, List<Item>>> getkartItems()
    {
        return ResponseEntity.ok(shoppingKartServce.getKartItems());
    }
    @PostMapping ("/additem")
    public ResponseEntity <String> addItem(@RequestBody Item item)
    {
        shoppingKartServce.addToKart(item);
        return ResponseEntity.ok("Item added Successfully!!!");
    }
    @PostMapping ("/removeitem")
    public ResponseEntity <String> removeItem(@RequestBody Item item)
    {
        shoppingKartServce.removeFromKart(item);
        return ResponseEntity.ok("Item removed Successfully!!!");
    }
    @GetMapping("/kartvalue")
    public ResponseEntity <BigDecimal> getKartValue()
    {
        return ResponseEntity.ok(shoppingKartServce.getKartValue());
    }
}
