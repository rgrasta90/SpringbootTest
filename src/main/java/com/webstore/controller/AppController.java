package com.webstore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webstore.AppRunner;
import com.webstore.model.CartItem;
import com.webstore.model.OrderDetails;
import com.webstore.model.Product;
import com.webstore.service.CartService;
import com.webstore.service.OrderService;
import com.webstore.service.PackageService;


@Controller
public class AppController {

	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	@Autowired
	PackageService service;
	@Autowired 
	CartService cartservice;	
	@Autowired
	OrderService orderservice;
	
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
	public @ResponseBody String addToCart(@RequestParam(name="gameid", required=false) String gameid, @RequestParam(name="username", required=false ) String username){
		System.out.println("In add to cart controller" + " " + username + " " + gameid);
		CartItem c = cartservice.addToCart(gameid, username);
		System.out.println("Succesfully added" + " " + c.getName() + "to cart" );
		OrderDetails o = orderservice.saveOrder(c.getName(), username);
		log.info("Succesfully created order" + " " + o.getId());
		return null;
	}
	
}
