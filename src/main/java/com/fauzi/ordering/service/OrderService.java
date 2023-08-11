package com.fauzi.ordering.service;

import com.fauzi.ordering.config.exception.BusinessException;
import com.fauzi.ordering.model.entity.Customer;
import com.fauzi.ordering.model.entity.Order;
import com.fauzi.ordering.model.entity.Product;
import com.fauzi.ordering.model.request.PostCreateOrderReq;
import com.fauzi.ordering.model.response.PostCreateOrderResp;
import com.fauzi.ordering.repository.CustomerRepository;
import com.fauzi.ordering.repository.OrderRespository;
import com.fauzi.ordering.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class OrderService {
    private CustomerRepository customerRepository;
    private OrderRespository orderRespository;
    private ProductRepository productRepository;

    public OrderService(CustomerRepository customerRepository, OrderRespository orderRespository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRespository = orderRespository;
        this.productRepository = productRepository;
    }

    public PostCreateOrderResp createOrder(PostCreateOrderReq req){
        Optional<Product> productOptional = productRepository.findById(req.getProductId());
        if (!productOptional.isPresent()){
            log.error("failed to retrieve product with Id {}",req.getProductId());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail product by that Id");
        }
        Product product = productOptional.get();

        Optional<Customer> custOptional = customerRepository.findById(req.getCustomerId());
        if (!custOptional.isPresent()){
            log.error("failed to retrieve customer with Id {}",req.getCustomerId());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail customer by that Id");
        }
        Customer cust = custOptional.get();

        if (product.getStock()>=req.getQuantity()){
            product.setStock(product.getStock()-req.getQuantity());
        }else{
            log.error("Product stock already run out with Id {}",req.getProductId());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Product run out stock");
        }
        productRepository.save(product);

        Order order = Order.builder()
                .customerName(cust.getCustomerName())
                .amount(BigDecimal.valueOf(req.getQuantity()).multiply(BigDecimal.valueOf(product.getProductPrice())))
                .customer(cust)
                .product(product)
                .quantity(req.getQuantity())
                .orderDate(new Timestamp(System.currentTimeMillis()))
                .build();
        orderRespository.save(order);

        return PostCreateOrderResp.builder()
                .orderId(order.getOrderId())
                .build();
    }
}
