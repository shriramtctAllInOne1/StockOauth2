package com.stock.oauth2.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Shriram
 *
 */
@SpringBootApplication
@ComponentScan("com.stock.oauth2.authserver")
public class AuthServer {

	public static void main(String[] args) {
		SpringApplication.run(AuthServer.class, args);

	}
}
