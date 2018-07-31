package com.oocl.dino_parking_system.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dino_parking_system.entities.Order;
import com.oocl.dino_parking_system.entities.Receipt;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ReceiptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ReceiptService receiptService;

    @MockBean
    private OrderService orderService;

    @Test
    public void postReceipt_ReturnReceipt() throws Exception {
        //given
        Receipt receipt = new Receipt("park");
        Order order = new Order("park", "a1234", "nohandle", receipt.getId());
        given(receiptService.generateReceipt()).willReturn(receipt);
        given(orderService.generateOrder(anyString(), anyString())).willReturn(order);

        //when
        ResultActions resultActions = mockMvc.perform(post("/receipts")
        .content("a1234"));

        //then
        resultActions.andExpect(jsonPath("$.status", is("park")));
    }
}
