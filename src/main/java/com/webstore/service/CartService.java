package com.webstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.model.CartItem;
import com.webstore.model.Product;
import com.webstore.repositories.CartRepository;
import com.webstore.repositories.ProductRepository;

@Service
public class CartService {
	@Autowired
	CartRepository repository;
	
	@Autowired
	ProductRepository prodrepository;
	
	public boolean addToCart(Long id, String user){
		Product p = prodrepository.findOne(id);
		CartItem c = new CartItem(p.getName(),p.getPrice(), user);
		repository.save(c);
		return false;
	}
}