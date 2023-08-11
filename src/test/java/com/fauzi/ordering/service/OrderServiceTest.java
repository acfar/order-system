package com.fauzi.ordering.service;

import com.fauzi.ordering.config.exception.BusinessException;
import com.fauzi.ordering.model.entity.Customer;
import com.fauzi.ordering.model.entity.Product;
import com.fauzi.ordering.model.request.PostCreateOrderReq;
import com.fauzi.ordering.repository.CustomerRepository;
import com.fauzi.ordering.repository.OrderRespository;
import com.fauzi.ordering.repository.ProductRepository;
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
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRespository orderRespository;

    @Test
    public void createOrderFailedCustTest() {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(BusinessException.class, () -> orderService.createOrder(PostCreateOrderReq.builder()
                        .customerId("custId")
                        .productId("prodId")
                        .quantity(5)
                        .build()));


        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
        assertEquals("Failed", thrown.getStatus());
    }

    @Test
    public void createOrderFailedProdTest() {
        Product product = Product.builder()
                .productId("prodId")
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(100)
                .build();
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(BusinessException.class, () -> orderService.createOrder(PostCreateOrderReq.builder()
                .customerId("custId")
                .productId("prodId")
                .quantity(5)
                .build()));


        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
        assertEquals("Failed", thrown.getStatus());

    }

    @Test
    public void createOrderSuccessTest() {
        Product product = Product.builder()
                .productId("prodId")
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(100)
                .build();
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        Customer customer = Customer.builder()
                .customerId("custId")
                .customerName("Testing1")
                .address("Jakarta")
                .phone(8712812)
                .build();
        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));

        orderService.createOrder(PostCreateOrderReq.builder()
                .customerId("custId")
                .productId("prodId")
                .quantity(5)
                .build());


        verify(productRepository).findById(anyString());
        verify(customerRepository).findById(anyString());
        verify(orderRespository).save(any());
    }
}
