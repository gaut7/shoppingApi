package com.assignment.shoppingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ShoppingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

}
