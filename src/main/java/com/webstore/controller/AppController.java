package com.webstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.collection.internal.PersistentBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import com.webstore.model.UserAccount;
import com.webstore.model.UserSession;
import com.webstore.service.OrderService;
import com.webstore.service.PackageService;
import com.webstore.service.UserService;


@Controller
public class AppController {

	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	@Autowired
	PackageService service;		
	@Autowired
	ApplicationContext context;
	@Autowired
	UserService userservice;
	@Autowired
	HttpSession session; 
	@Autowired
	OrderService orderservice;
	

	
	@RequestMapping("/")
	public String welcome(){
		System.out.println("In Loginr");
		log.info("SESSION ID" +" " + session.getId());
		return "login.html";
	}
	
	@RequestMapping("/getproducts")
	public 	@ResponseBody List<Product> getDetails(){
		log.info("Getting al proucts");
		
		
		
		return service.getAllProducts();
	}
	
	@RequestMapping(value = "/addtocart", method = RequestMethod.POST)
	public @ResponseBody CartItem addToCart(@RequestBody Product pr){	
		UserSession user = context.getBean(UserSession.class);
		CartItem cart = context.getBean(CartItem.class);
		cart.setUsername(user.getUsername());
		Product p = service.getById0(pr.getId());

			cart.addProduct(p);
			cart.getSum();


		log.info("Added to cart: " + p.getName());
		return cart;
	}
	
	@RequestMapping(value="/removefromcart", method = RequestMethod.POST)
	public @ResponseBody CartItem removeFromCart(@RequestBody Product pr){
		
		CartItem cart = context.getBean(CartItem.class);
		cart.removeItem(pr);
		cart.getSum();
		return cart;
	}
	
	@RequestMapping(value="/createuser", method=RequestMethod.POST)
	public @ResponseBody UserAccount createUser(@RequestBody UserAccount u){
		log.info("Creating user");
		u = userservice.createUser(u);
		log.info("Created user: " + u.getName());

		return  u;
	}
	
	@RequestMapping(value="/validateuser", method=RequestMethod.GET)
	public @ResponseBody UserAccount userExists(@RequestParam(name="name") String name){
		log.info("In validate controller");
		return this.userservice.userExists(name);
		
	}

	@RequestMapping(value="/loginaction", method=RequestMethod.POST)
	public String login(@RequestParam(name="username") String uname,
			@RequestParam(name="password") String pwd){
		
		UserAccount u = this.userservice.login(uname, pwd);
	if(!(u == null)){
		UserSession user = context.getBean(UserSession.class);
		user.setUsername(u.getName());
		log.info(String.valueOf(u.hashCode()));
		
		return "redirect:welcome.html";
	}
	else
		return "redirect:login.html";
		
	}
	
	@RequestMapping(value="/getusersession", method=RequestMethod.GET)
	public @ResponseBody UserSession getUserSession(){
		UserSession user = context.getBean(UserSession.class);
 	 	log.info("returning" + user.getUsername());
		return user;
	}
	
	@RequestMapping(value="/getcart", method=RequestMethod.GET)
	public @ResponseBody CartItem getCart(@RequestParam(name="username") String uname ){
		CartItem cart = context.getBean(CartItem.class);

		 return cart;
	}
	@RequestMapping(value="/submitorder", method=RequestMethod.POST)
	public String generateOrder(){
		CartItem cart = context.getBean(CartItem.class);
		this.orderservice.submitOrder(cart);
     	return "redirect:orderdetails.html";
	}
	
	@RequestMapping(value="/getorders", method=RequestMethod.GET)
	public @ResponseBody List<OrderDetails> getOrders(){
		UserSession user = context.getBean(UserSession.class);
		List<OrderDetails> orders = this.orderservice.findOrdersByUsername(user.getUsername());
		OrderDetails o = orders.get(0);
 	
 	
		return orders;
	}
}
