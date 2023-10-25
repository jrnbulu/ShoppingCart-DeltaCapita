package org.dc.sc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dc.sc.pojos.Item;
import org.dc.sc.service.ShoppingKartServce;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ShoppingKartController.class)
class ShoppingKartControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ShoppingKartServce shoppingKartServce;

    @Test
    void test_getAllItems() throws Exception {
        when(shoppingKartServce.getAllItems()).thenReturn(new HashMap<String,List<Item>>());
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        get("/api/shoppingkart/allitems")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void test_getItems() throws Exception {
        when(shoppingKartServce.getItemsByName(anyString())).thenReturn(List.of(Item.builder().build()));
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        get("/api/shoppingkart/item/lemon")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void test_getKartItems() throws Exception {
        when(shoppingKartServce.getKartItems()).thenReturn(new HashMap<String,List<Item>>());
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        get("/api/shoppingkart/kartitems")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void test_addItem() throws Exception {
        doNothing().when(shoppingKartServce).addToKart(any(Item.class));
        String itemJson = new ObjectMapper().writeValueAsString(Item.builder().build());
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        post("/api/shoppingkart/additem")
                .contentType(MediaType.APPLICATION_JSON).content(itemJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void test_removeItem() throws Exception {
        doNothing().when(shoppingKartServce).removeFromKart(any(Item.class));
        String itemJson = new ObjectMapper().writeValueAsString(Item.builder().build());
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        post("/api/shoppingkart/removeitem")
                        .contentType(MediaType.APPLICATION_JSON).content(itemJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    void test_getKartValue() throws Exception {
        when(shoppingKartServce.getKartValue()).thenReturn(new BigDecimal(0));
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        get("/api/shoppingkart/kartvalue")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

}
