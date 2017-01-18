package com.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.model.OrderDetails;
import com.webstore.model.Product;
import com.webstore.repositories.*;

@Service
public class OrderService {

	//@Autowired
	//OrderRepository repository;
	@Autowired
	ProductRepository productrepository;
	
	public OrderDetails saveOrder(String name, String username){
	//	Product p = null;
		//p = productrepository.findByName(name);
		//OrderDetails o = new OrderDetails(p.getName(),p.getPrice(),username);
		return null;
	}
	
}
