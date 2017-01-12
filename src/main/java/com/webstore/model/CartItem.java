package com.webstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CartItem {

protected CartItem(){
		
	}
	
	public CartItem(String name, int price, String desc){
		this.name=name;
		this.price = price;	
		this.user=desc;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private int price;
	private String user;
}
