package com.fauzi.ordering.controller;

import com.fauzi.ordering.model.request.PostProductReq;
import com.fauzi.ordering.model.request.PutProductReq;
import com.fauzi.ordering.model.response.GetProductIdResp;
import com.fauzi.ordering.model.response.ProductResp;
import com.fauzi.ordering.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/v1/product/{id}")
    public GetProductIdResp getCustomerById (@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping(value = "/v1/product")
    public ProductResp createCustomer (@RequestBody @Valid PostProductReq postProductReq) {
        return productService.createProduct(postProductReq);
    }

    @PutMapping(value = "/v1/product")
    public ProductResp updateCustomer (@RequestBody @Valid PutProductReq putProductReq) {
        return productService.updateProduct(putProductReq);
    }
}
