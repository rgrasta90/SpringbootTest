package com.webstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderDetails {
	
	public OrderDetails(){
		
	}
	

	
	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String username;
	private long totalprice;
	
	//@OneToMany(mappedBy="order")
	//private List<Product> products = new ArrayList<Product>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(long totalprice) {
		this.totalprice = totalprice;
	}

	/*public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		for(Product x : products){
			x.setOrder(this);
			this.products.add(x);
		}*/
	}
	
	


