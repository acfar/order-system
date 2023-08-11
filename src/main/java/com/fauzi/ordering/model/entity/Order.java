package com.fauzi.ordering.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="\"order\"")
public class Order {
    @Id
    @Column(name="order_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "order_fk1"), nullable = false)
    private Customer customer;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "order_fk2"),  nullable = false)
    private Product product;

    @Column(name = "order_date")
    private Timestamp orderDate;
}
