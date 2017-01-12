package com.webstore.config;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.jdbc.Connection;

@Configuration
@ComponentScan("com.webstore.controller")
@EnableAutoConfiguration
public class AppRunner {
	 @Autowired
	   private static ApplicationContext applicationContext;
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
    public DataSource dataSource() throws SQLException {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
             
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
          // Connection con= (Connection) dataSource.getConnection();
            return dataSource;
    }

}
