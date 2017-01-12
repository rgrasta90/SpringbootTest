package com.webstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webstore.model.Product;
import com.webstore.repositories.ProductRepository;

@Service
public class PackageService {

	@Autowired
	ProductRepository repository;
	
	public List<Product> getAllProducts(){
		ArrayList<Product> products = (ArrayList<Product>) repository.findAll();
		return products;
	}
}
