package com.fauzi.ordering.service;

import com.fauzi.ordering.config.exception.BusinessException;
import com.fauzi.ordering.model.entity.Product;
import com.fauzi.ordering.model.request.PostProductReq;
import com.fauzi.ordering.model.request.PutProductReq;
import com.fauzi.ordering.model.response.GetProductIdResp;
import com.fauzi.ordering.model.response.ProductResp;
import com.fauzi.ordering.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public GetProductIdResp getProductById(String custId){
        Optional<Product> productOptional = productRepository.findById(custId);
        if (!productOptional.isPresent()){
            log.error("failed to retrieve product with Id {}",custId);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail product by that Id");
        }
        Product product = productOptional.get();
        return GetProductIdResp.builder()
                .productId(product.getProductId())
                .productPrice(product.getProductPrice())
                .productDescription(product.getProductDescription())
                .stock(product.getStock())
                .build();
    }

    public ProductResp createProduct(PostProductReq req){
        Product product = Product.builder()
                .productDescription(req.getProductDescription())
                .productPrice(req.getProductPrice())
                .stock(req.getStock())
                .build();

        productRepository.save(product);
        return ProductResp.builder()
                .productId(product.getProductId())
                .build();
    }

    public ProductResp updateProduct(PutProductReq req){
        Optional<Product> productOptional = productRepository.findById(req.getProductId());
        if (!productOptional.isPresent()){
            log.error("failed to retrieve product with Id {}",req.getProductId());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail product by that Id");
        }
        Product product = productOptional.get();
        if (StringUtils.isNotBlank(req.getProductDescription())){
            product.setProductDescription(req.getProductDescription());
        }
        if (Objects.nonNull(req.getProductPrice())){
            product.setProductPrice(req.getProductPrice());
        }
        if (Objects.nonNull(req.getStock())){
            product.setStock(req.getStock());
        }
        productRepository.save(product);
        return ProductResp.builder()
                .productId(product.getProductId())
                .build();
    }
}
