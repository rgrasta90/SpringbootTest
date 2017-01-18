package com.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.model.CartItem;
import com.webstore.model.Product;
//import com.webstore.repositories.OrderRepository;
import com.webstore.repositories.ProductRepository;
/*
@Service
public class CartService {
	@Autowired
	CartRepository repository;
	
	@Autowired
	ProductRepository prodrepository;

	
	public CartItem addToCart(String id, String user){
		Product p = prodrepository.findOne(Long.valueOf(id));
		CartItem c = new CartItem(p.getName(),p.getPrice(), user);
		c = repository.save(c);
	
		return c;
	}
}
*/