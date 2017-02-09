package com.webstore.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webstore.model.OrderDetails;

public interface OrderRepository extends CrudRepository<OrderDetails, Long> {

	public List<OrderDetails> findByUsername(String username);
}
