package com.fauzi.ordering.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerIdResp {
    private String customerId;
    private String customerName;
    private String address;
    private Integer phone;
}
