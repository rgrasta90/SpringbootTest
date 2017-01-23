package com.webstore.controller;

import java.util.List;

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
	OrderService orderservice;

	@Autowired
	ApplicationContext context;
	
	@Autowired
	UserService userservice;
	 
	CartItem cart;	
	UserSession user;
	
	@RequestMapping("/")
	public String welcome(){
		System.out.println("In Loginr");
		return "login.html";
	}
	
	@RequestMapping("/getproducts")
	public 	@ResponseBody List<Product> getDetails(){
		log.info("Getting al proucts");
		
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
		//OrderDetails o = orderservice.saveOrder(c.getName(), username);
	//	log.info("Succesfully created order" + " " + o.getId());
		log.info("Added to cart: " + p.getName());
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
		this.user = context.getBean(UserSession.class);
		this.user.setUsername(u.getName());
		return "redirect:welcome.html";
	}
	else
		return "redirect:login.html";
		
	}
	
	@RequestMapping(value="/getusersession", method=RequestMethod.GET)
	public @ResponseBody UserSession getUserSession(){
 	 	
		return this.user;
	}
}
