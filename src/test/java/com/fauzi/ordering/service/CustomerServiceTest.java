package com.fauzi.ordering.service;

import com.fauzi.ordering.config.exception.BusinessException;
import com.fauzi.ordering.model.entity.Customer;
import com.fauzi.ordering.model.request.PostCustomerReq;
import com.fauzi.ordering.model.request.PutCustomerReq;
import com.fauzi.ordering.model.response.CustomerResp;
import com.fauzi.ordering.model.response.GetCustomerIdResp;
import com.fauzi.ordering.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void customerGetByIdSuccessTest() {
        Customer customer = Customer.builder()
                .customerId("custId")
                .customerName("Testing1")
                .address("Jakarta")
                .phone(8712812)
                .build();
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));

        GetCustomerIdResp actualResponse = customerService.getCustById("custId");

        assertEquals(customer.getCustomerName(), actualResponse.getCustomerName());
        assertEquals(customer.getPhone(), actualResponse.getPhone());
        assertEquals(customer.getAddress(), actualResponse.getAddress());

    }

    @Test
    public void customerGetByIdFailedTest() {
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(BusinessException.class, () -> customerService.getCustById("custId"));

        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
        assertEquals("Failed", thrown.getStatus());

    }

    @Test
    public void createCustomerSuccessTest() {
        PostCustomerReq req = PostCustomerReq.builder()
                .customerName("testing1")
                .address("Jakarta")
                .phone(8287212)
                .build();

        customerService.createCustomer(req);

        verify(customerRepository).save(any());

    }

    @Test
    public void updateCustomerFailedTest() {
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(BusinessException.class, () -> customerService.updateCustomer(PutCustomerReq.builder()
                .customerName("testing1")
                .address("Jakarta")
                .phone(86183312)
                .customerId("custId")
                .build()));

        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
        assertEquals("Failed", thrown.getStatus());

    }

    @Test
    public void updateCustomerSuccessTest() {
        Customer customer = Customer.builder()
                .customerName("testing1")
                .customerId("custId")
                .address("Jakarta")
                .phone(86183312)
                .build();
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));

        CustomerResp resp =  customerService.updateCustomer(PutCustomerReq.builder()
                .customerName("testing1")
                .address("Jakarta")
                .phone(86183312)
                .customerId("custId")
                .build());

        assertEquals(customer.getCustomerId(), resp.getCustomerId());

    }
}
