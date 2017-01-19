package com.webstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webstore.AppRunner;
import com.webstore.model.CartItem;
import com.webstore.model.OrderDetails;
import com.webstore.model.Product;
import com.webstore.service.OrderService;
import com.webstore.service.PackageService;


@Controller
public class AppController {

	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	@Autowired
	PackageService service;
	//@Autowired 
//	CartService cartservice;	
	@Autowired
	OrderService orderservice;

	@Autowired
	ApplicationContext context;
	CartItem cart;
	
	@RequestMapping("/")
	public String welcome(){
		System.out.println("In welcome controller");
		return "welcome.html";
	}
	
	@RequestMapping("/getproducts")
	public 	@ResponseBody List<Product> getDetails(){
		System.out.println("In get all products controller");
		return service.getAllProducts();
	}
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.POST)
	//public @ResponseBody Product addToCart(@RequestParam(name="gameid", required=false) long gameid){
	public @ResponseBody CartItem addToCart(@RequestBody Product pr){	
//	System.out.println("In add to cart controller" + " " + " " + gameid);
		cart = context.getBean(CartItem.class);
		Product p = service.getById0(pr.getId());
		cart.addProduct(p);
		cart.getSum();
	//	CartItem c = cartservice.addToCart(gameid, username);
		System.out.println("Succesfully added" + " " + p.getName() + "to cart" );
		//OrderDetails o = orderservice.saveOrder(c.getName(), username);
	//	log.info("Succesfully created order" + " " + o.getId());
		return cart;
	}
	
	@RequestMapping(value="/removefromcart", method = RequestMethod.POST)
	public @ResponseBody CartItem removeFromCart(@RequestBody Product pr){
		if(cart == null){
		   cart = context.getBean(CartItem.class);
		}
		cart.removeItem(pr);
		cart.getSum();
		
		return cart;
	}
	
}
