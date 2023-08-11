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
public class PostCustomerReq {
    @NotBlank(message = "customerName is required")
    private String customerName;

    @NotBlank(message = "address is required")
    private String address;

    private Integer phone;
}
