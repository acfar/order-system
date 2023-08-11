package com.fauzi.ordering.controller;

import com.fauzi.ordering.model.request.PostCreateOrderReq;
import com.fauzi.ordering.model.request.PostProductReq;
import com.fauzi.ordering.model.response.PostCreateOrderResp;
import com.fauzi.ordering.model.response.ProductResp;
import com.fauzi.ordering.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/v1/order")
    public PostCreateOrderResp createOrder(@RequestBody @Valid PostCreateOrderReq postCreateOrderReq) {
        return orderService.createOrder(postCreateOrderReq);
    }
}
