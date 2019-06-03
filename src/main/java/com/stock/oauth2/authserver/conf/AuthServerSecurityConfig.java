package com.stock.oauth2.authserver.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.stock.oauth2.authserver.library.MongoUserDetailsService;
import com.stock.oauth2.utill.AuthConstant;

/**
 * @author shriram
 *
 */
@Configuration
public class AuthServerSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * MongoUserDetailsService
	 */
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return new MongoUserDetailsService();
	}

	/**
	 * AuthenticationManagerBuilder
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	/**
	 * httpSecurity configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers(AuthConstant.TOKEN_URL);
	}

	/**
	 * httpwebSecurity configuration
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	/**
	 * AuthenticationManager Bean
	 */
	@Bean(name = "authenticationManager")
	@Lazy
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
