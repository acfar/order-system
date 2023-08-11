package com.fauzi.ordering.controller;

import com.fauzi.ordering.model.request.PostCreateOrderReq;
import com.fauzi.ordering.model.response.PostCreateOrderResp;
import com.fauzi.ordering.service.OrderService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        orderController = new OrderController(orderService);
        mockMvc = standaloneSetup(orderController).build();
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void createCustTest() throws Exception {
        PostCreateOrderReq req = PostCreateOrderReq.builder()
                .customerId("custId")
                .productId("prodId")
                .quantity(2)
                .build();

        PostCreateOrderResp response = PostCreateOrderResp.builder()
                .orderId("orderId")
                .build();

        when(orderService.createOrder(req)).thenReturn(response);

        mockMvc.perform(post("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(req))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(orderService).createOrder(any());
    }
}
