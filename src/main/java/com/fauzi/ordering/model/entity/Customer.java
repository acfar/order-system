package com.fauzi.ordering.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
public class Customer {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private Integer phone;
}
