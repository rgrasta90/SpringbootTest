package com.webstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.model.CartItem;
import com.webstore.model.OrderDetails;
import com.webstore.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	public OrderDetails submitOrder(CartItem c){
		OrderDetails o = new OrderDetails();
		o.setUsername(c.getUsername());
		o.setTotalprice(c.getTotalPrice());
		o.setProducts(c.getProducts());
		
		return this.repository.save(o);	 
	}
	
	public OrderDetails findOrder(Long id){
		return this.repository.findOne(id);
	}
	
	public List<OrderDetails> findOrdersByUsername(String username){
		return this.repository.findByUsername(username);
	}
}
