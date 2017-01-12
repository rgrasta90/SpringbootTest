package com.webstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webstore.model.Product;
import com.webstore.service.PackageService;


@Controller
public class AppController {

	@Autowired
	PackageService service;
	
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
	
	@RequestMapping("/addtocart/{id}/{user}")
	public @ResponseBody String addToCart(@RequestParam Long id, @RequestParam String user){
		
		return null;
	}

}
