package com.webstore.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webstore.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByName(String name);
}
