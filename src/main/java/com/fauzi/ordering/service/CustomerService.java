package com.fauzi.ordering.service;

import com.fauzi.ordering.config.exception.BusinessException;
import com.fauzi.ordering.model.entity.Customer;
import com.fauzi.ordering.model.request.PostCustomerReq;
import com.fauzi.ordering.model.request.PutCustomerReq;
import com.fauzi.ordering.model.response.CustomerResp;
import com.fauzi.ordering.model.response.GetCustomerIdResp;
import com.fauzi.ordering.repository.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public GetCustomerIdResp getCustById(String custId){
        Optional<Customer> custOptional = customerRepository.findById(custId);
        if (!custOptional.isPresent()){
            log.error("failed to retrieve customer with Id {}",custId);
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail customer by that Id");
        }
        Customer cust = custOptional.get();
        return GetCustomerIdResp.builder()
                .customerId(cust.getCustomerId())
                .address(cust.getAddress())
                .customerName(cust.getCustomerName())
                .phone(cust.getPhone())
                .build();
    }

    public CustomerResp createCustomer(PostCustomerReq req){
        Customer cust = Customer.builder()
                .customerName(req.getCustomerName())
                .address(req.getAddress())
                .phone(req.getPhone())
                .build();

        customerRepository.save(cust);
        return CustomerResp.builder()
                .customerId(cust.getCustomerId())
                .build();
    }

    public CustomerResp updateCustomer(PutCustomerReq req){
        Optional<Customer> custOptional = customerRepository.findById(req.getCustomerId());
        if (!custOptional.isPresent()){
            log.error("failed to retrieve customer with Id {}",req.getCustomerId());
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Failed", "Can't find detail customer by that Id");
        }
        Customer cust = custOptional.get();
        if (StringUtils.isNotBlank(req.getCustomerName())){
            cust.setCustomerName(req.getCustomerName());
        }
        if (StringUtils.isNotBlank(req.getAddress())){
            cust.setAddress(req.getAddress());
        }
        if (Objects.nonNull(req.getPhone())){
            cust.setPhone(req.getPhone());
        }
        customerRepository.save(cust);
        return CustomerResp.builder()
                .customerId(cust.getCustomerId())
                .build();
    }
}
