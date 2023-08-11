package com.fauzi.ordering.controller;

import com.fauzi.ordering.model.request.PostProductReq;
import com.fauzi.ordering.model.request.PutProductReq;
import com.fauzi.ordering.model.response.GetProductIdResp;
import com.fauzi.ordering.model.response.ProductResp;
import com.fauzi.ordering.service.ProductService;
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
public class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        productController = new ProductController(productService);
        mockMvc = standaloneSetup(productController).build();
    }

    @AfterEach
    public void tearDown() {
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void getProductByIdTest() throws Exception {
        GetProductIdResp response = GetProductIdResp.builder()
                .productId("prodId")
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(100)
                .build();

        when(productService.getProductById(anyString())).thenReturn(response);

        mockMvc.perform(get("/v1/product/prodId")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).getProductById(anyString());
    }

    @Test
    public void createProductTest() throws Exception {
        PostProductReq req = PostProductReq.builder()
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(100)
                .build();

        ProductResp response = ProductResp.builder()
                .productId("prodId")
                .build();

        when(productService.createProduct(req)).thenReturn(response);

        mockMvc.perform(post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(req))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).createProduct(any());
    }


    @Test
    public void updateProductTest() throws Exception {
        PutProductReq req = PutProductReq.builder()
                .productId("prodId")
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(100)
                .build();

        ProductResp response = ProductResp.builder()
                .productId("prodId")
                .build();

        when(productService.updateProduct(req)).thenReturn(response);

        mockMvc.perform(put("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(req))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productService).updateProduct(any());
    }

}
