package com.fauzi.ordering.model.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutCustomerReq {
    @NotBlank(message = "customerId is required")
    private String customerId;

    private String customerName;
    private String address;
    private Integer phone;
}
