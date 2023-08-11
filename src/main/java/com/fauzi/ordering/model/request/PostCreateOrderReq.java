package com.fauzi.ordering.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateOrderReq {
    @NotBlank(message = "productId is required")
    private String productId;

    @NotBlank(message = "customerId is required")
    private String customerId;

    @NotNull(message = "quantity is required")
    private Integer quantity;
}
