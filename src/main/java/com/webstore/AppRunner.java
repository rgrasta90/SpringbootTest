package com.webstore;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.Connection;
import com.webstore.model.Product;
import com.webstore.repositories.ProductRepository;

@Configuration
@ComponentScan({"com.webstore.controller","com.webstore.repositories","com.webstore.model", "com.webstore.service"})
@EnableAutoConfiguration
@EnableJpaRepositories("com.webstore.repositories")
public class AppRunner {

	@Autowired
	ProductRepository repository;
	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);
	
	@PostConstruct
	 public void greet(){
		 System.out.println("Hello");
			repository.save(new Product("Fifa 2017", 999, "Newest Fifa version, incluing new teams and leagues from all over the world"));
			repository.save(new Product("Borderlands", 500,"Join the adventure on this single and cooperative first person shooter"));
			repository.save(new Product("Watchdogs 2", 1200,"Play as a hacker trying to defeat an evil coorporation"));
			repository.save(new Product("Diablo", 300,"Fight against differnt types of enemies in this RPG game"));
			
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
	
	/*@Bean
    public DataSource dataSource() throws SQLException {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
             
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
          // Connection con= (Connection) dataSource.getConnection();
            return dataSource;
    }
    */

}
