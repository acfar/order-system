package com.fauzi.ordering.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductIdResp {
    private String productId;
    private String productDescription;
    private Integer productPrice;
    private Integer stock;
}
