package com.fauzi.ordering.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String productId;

    @Column(name = "product_price")
    private Integer productPrice;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "stock")
    private Integer stock;
}
