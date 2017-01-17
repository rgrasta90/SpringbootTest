package com.webstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webstore.model.OrderDetails;

public interface OrderRepository extends CrudRepository<OrderDetails, Long> {

}
