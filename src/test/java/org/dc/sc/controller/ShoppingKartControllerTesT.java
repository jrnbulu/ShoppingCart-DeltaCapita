package org.dc.sc.controller;

import org.dc.sc.controller.ShoppingkartController;
import org.dc.sc.pojos.Item;
import org.dc.sc.service.ShoppingKartServce;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(
//        webEnvironment = RANDOM_PORT,
//        properties = {"spring.cloud.config.enabled=false"})
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ShoppingkartController.class,properties = {"spring.cloud.config.enabled=false"})
class ShoppingKartControllerTesT {

    private MockMvc mvc;

    @Mock
    private ShoppingKartServce shoppingKartServce;

    @Test
    void getAllItems() throws Exception {
        when(shoppingKartServce.getAllItems()).thenReturn(new HashMap<String,List<Item>>());
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                        get("/api/shoppingkart/allitems")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertEquals(response.getStatus(),HttpStatus.OK.value());
    }

}
