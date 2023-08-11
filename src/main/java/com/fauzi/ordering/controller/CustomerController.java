package com.fauzi.ordering.controller;

import com.fauzi.ordering.model.request.PostCustomerReq;
import com.fauzi.ordering.model.request.PutCustomerReq;
import com.fauzi.ordering.model.response.CustomerResp;
import com.fauzi.ordering.model.response.GetCustomerIdResp;
import com.fauzi.ordering.service.CustomerService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/v1/customer/{id}")
    public GetCustomerIdResp getCustomerById (@PathVariable String id) {
        return customerService.getCustById(id);
    }

    @PostMapping(value = "/v1/customer")
    public CustomerResp createCustomer (@RequestBody @Valid PostCustomerReq postCustomerReq) {
        return customerService.createCustomer(postCustomerReq);
    }

    @PutMapping(value = "/v1/customer")
    public CustomerResp updateCustomer (@RequestBody @Valid PutCustomerReq putCustomerReq) {
        return customerService.updateCustomer(putCustomerReq);
    }

}
