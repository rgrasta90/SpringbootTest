package com.webstore;

import java.sql.SQLException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mysql.jdbc.Connection;
import com.webstore.model.CartItem;
import com.webstore.model.Product;
import com.webstore.model.UserSession;
import com.webstore.repositories.ProductRepository;
import com.webstore.repositories.TestRepository;

@Configuration
@ComponentScan({"com.webstore.controller","com.webstore.repositories","com.webstore.model", "com.webstore.service"})
@EnableAutoConfiguration
@EnableJpaRepositories("com.webstore.repositories")
public class AppRunner {

	@Autowired
	ProductRepository repository;
	@Autowired
	TestRepository repo;
	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	
	 @PostConstruct
	 public void loadProducts(){
		 System.out.println("Hello");
			repository.save(new Product("Fifa 2017", 999, "Newest Fifa version, incluing new teams and leagues from all over the world"));
			repository.save(new Product("Borderlands", 500,"Join the adventure on this single and cooperative first person shooter"));
			repository.save(new Product("Watchdogs 2", 1200,"Play as a hacker trying to defeat an evil coorporation"));
			repository.save(new Product("Diablo", 300,"Fight against differnt types of enemies in this RPG game"));
			repository.save(new Product("Dark souls", 700,"RPG action gane where you will fight against hardest bosses ever"));
			
			repo.insert(new Product("Red dead redemption", 999, "Western open world game"));
			// fetch all Products
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Product customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
	 }
	 

	public static void main(String[] args){
		SpringApplication.run(AppRunner.class, args);
	}
	

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
	} 
	
	@Bean
	@Scope("session")
    public CartItem cartItem() {
        return new CartItem();
    }
	
	@Bean
	@Scope("session")
    public UserSession userSession() {
        return new UserSession();
    }
	

    

}
