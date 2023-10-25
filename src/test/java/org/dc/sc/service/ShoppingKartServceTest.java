package org.dc.sc.service;

import org.dc.sc.kart.UserShoppingKart;
import org.dc.sc.pojos.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingKartServceTest {

    @Mock
    UserShoppingKart userShoppingKart;
    @InjectMocks
    ShoppingKartServce shoppingKartServce;

    @BeforeEach
    public void setup(){
        ReflectionTestUtils.setField(shoppingKartServce,"userShoppingKart",userShoppingKart);
    }

        @Test
    void test_getAllItems()
    {
        assertNotNull(shoppingKartServce.getAllItems());
    }
    @Test
    void test_getItemsByName()
    {
        assertNull(shoppingKartServce.getItemsByName("lemon"));
    }
    @Test
    void test_getKartItems()
    {
        assertNotNull(shoppingKartServce.getKartItems());
    }
    @Test
    void test_addToKart()
    {
        when(userShoppingKart.getKart()).thenReturn(new HashMap<>());
        shoppingKartServce.addToKart(Item.builder().itemName("test").build());
        assertNotNull(shoppingKartServce.getKartItems());
    }
    @Test
    void test_removeFromKart()
    {
        when(userShoppingKart.getKart()).thenReturn(new HashMap<>());
        shoppingKartServce.removeFromKart(Item.builder().itemName("test").build());
        assertNotNull(shoppingKartServce.getKartItems());
    }
}
