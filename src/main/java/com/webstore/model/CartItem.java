package com.webstore.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

@Scope(value="session")
public class CartItem {

	private List<Product> products = new ArrayList<Product>();
	private long totalPrice;
	
	public void addProduct(Product p){
		this.products.add(p);
	}
	
	public long getSum(){
		long sum = 0;
		for(Product p: products){
		sum = sum + p.getPrice();	
		}
		return sum;
	}

}
