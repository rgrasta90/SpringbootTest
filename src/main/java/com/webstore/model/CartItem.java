package com.webstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;


public class CartItem {

	public CartItem(){
		System.out.println("Creating new CartItem");
	}
	

	private long id;
	private List<Product> products = new ArrayList<Product>();
	private long totalPrice;

	private String username;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void addProduct(Product p){
		this.products.add(p);
	}
	

	public List<Product> getProducts(){
		return this.products;
	}
	
	public long getTotalPrice(){
		return this.totalPrice;
		
	}
	public void getSum(){
		long sum = 0;
		for(Product p: products){
		sum = sum + p.getPrice();	
		}
		
		this.totalPrice = sum;
	}
	
	public void removeItem(Product pr){
		for(Product p : this.products){
			if(pr.getName().equalsIgnoreCase(p.getName())){
				this.getProducts().remove(p);
				break;
			}
		}
		
	}

}
