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
public class PutProductReq {
    @NotBlank(message = "productId is required")
    private String productId;
    private String productDescription;
    private Integer productPrice;
    private Integer stock;
}
