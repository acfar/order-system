package com.fauzi.ordering.repository;

import com.fauzi.ordering.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRespository extends JpaRepository<Order, String> {
}
