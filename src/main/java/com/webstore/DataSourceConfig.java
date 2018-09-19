package com.webstore;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mysql.jdbc.Connection;


public class DataSourceConfig {

    public DataSource dataSource() throws SQLException {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();     
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/shop");
            dataSource.setUsername("root");
            dataSource.setPassword("admin");
            Connection con= (Connection) dataSource.getConnection();
            return dataSource;
    }
	
}
