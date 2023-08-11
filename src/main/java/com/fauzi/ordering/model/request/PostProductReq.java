package com.fauzi.ordering.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostProductReq {
    @NotBlank(message = "productDescription is required")
    private String productDescription;

    @NotNull(message = "productPrice is required")
    private Integer productPrice;

    @NotNull(message = "stock is required")
    private Integer stock;
}
