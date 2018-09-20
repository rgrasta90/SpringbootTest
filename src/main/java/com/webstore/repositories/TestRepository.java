package com.webstore.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webstore.model.Product;
@Transactional
@Repository
public class TestRepository {

	
	@Autowired
	EntityManager em;
	
	public Product insert(Product p) {
		Product pr = em.merge(p);
		return pr;

	}
}
