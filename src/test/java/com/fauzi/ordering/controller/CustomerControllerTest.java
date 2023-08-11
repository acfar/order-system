package com.fauzi.ordering.controller;

import com.fauzi.ordering.model.request.PostCustomerReq;
import com.fauzi.ordering.model.request.PutCustomerReq;
import com.fauzi.ordering.model.response.CustomerResp;
import com.fauzi.ordering.model.response.GetCustomerIdResp;
import com.fauzi.ordering.service.CustomerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService customerService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        customerController = new CustomerController(customerService);
        mockMvc = standaloneSetup(customerController).build();
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void getCustByIdTest() throws Exception {
        GetCustomerIdResp response = GetCustomerIdResp.builder()
                .customerId("custId")
                .customerName("Testing1")
                .address("Jakarta")
                .phone(8712812)
                .build();

        when(customerService.getCustById(anyString())).thenReturn(response);

        mockMvc.perform(get("/v1/customer/custId")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(customerService).getCustById(anyString());
    }

    @Test
    public void createCustTest() throws Exception {
        PostCustomerReq req = PostCustomerReq.builder()
                .customerName("testing1")
                .address("Jakarta")
                .phone(8287212)
                .build();

        CustomerResp response = CustomerResp.builder()
                .customerId("custId")
                .build();

        when(customerService.createCustomer(req)).thenReturn(response);

        mockMvc.perform(post("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(req))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(customerService).createCustomer(any());
    }


    @Test
    public void updateCustTest() throws Exception {
        PutCustomerReq req = PutCustomerReq.builder()
                .customerId("custId")
                .customerName("testing1")
                .address("Jakarta")
                .phone(8287212)
                .build();

        CustomerResp response = CustomerResp.builder()
                .customerId("custId")
                .build();

        when(customerService.updateCustomer(req)).thenReturn(response);

        mockMvc.perform(put("/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(req))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(customerService).updateCustomer(any());
    }

}
