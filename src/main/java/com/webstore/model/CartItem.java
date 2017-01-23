package com.webstore.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;


@Scope("session")
public class CartItem {

	private List<Product> products = new ArrayList<Product>();
	private long totalPrice;
	
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
