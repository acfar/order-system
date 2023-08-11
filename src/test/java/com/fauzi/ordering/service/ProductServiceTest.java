package com.fauzi.ordering.service;

import com.fauzi.ordering.config.exception.BusinessException;
import com.fauzi.ordering.model.entity.Product;
import com.fauzi.ordering.model.request.PostProductReq;
import com.fauzi.ordering.model.request.PutProductReq;
import com.fauzi.ordering.model.response.GetProductIdResp;
import com.fauzi.ordering.model.response.ProductResp;
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
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void productGetByIdSuccessTest() {
        Product product = Product.builder()
                .productId("prodId")
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(100)
                .build();
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        GetProductIdResp actualResponse = productService.getProductById("custId");

        assertEquals(product.getProductDescription(), actualResponse.getProductDescription());
        assertEquals(product.getProductPrice(), actualResponse.getProductPrice());
        assertEquals(product.getStock(), actualResponse.getStock());

    }

    @Test
    public void productGetByIdFailedTest() {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(BusinessException.class, () -> productService.getProductById("custId"));

        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
        assertEquals("Failed", thrown.getStatus());

    }

    @Test
    public void createProductSuccessTest() {
        PostProductReq product = PostProductReq.builder()
                .productPrice(5000)
                .productDescription("fanta")
                .stock(10)
                .build();

        productService.createProduct(product);

        verify(productRepository).save(any());

    }

    @Test
    public void updateProductFailedTest() {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException thrown = assertThrows(BusinessException.class, () -> productService.updateProduct(PutProductReq.builder()
                        .productDescription("fanta")
                        .productId("prodId")
                        .stock(500)
                        .productPrice(5000)
                        .build()));

        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
        assertEquals("Failed", thrown.getStatus());

    }

    @Test
    public void updateProductSuccessTest() {
        Product product = Product.builder()
                .productId("prodId")
                .productDescription("Fanta")
                .productPrice(5000)
                .stock(500)
                .build();
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        ProductResp resp =  productService.updateProduct(PutProductReq.builder()
                .productDescription("fanta")
                .productId("prodId")
                .stock(500)
                .productPrice(5000)
                .build());

        assertEquals(product.getProductId(), resp.getProductId());

    }
}
