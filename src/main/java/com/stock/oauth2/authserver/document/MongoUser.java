package com.stock.oauth2.authserver.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * @author shriram
 *
 */
@Document(collection = "user")
public class MongoUser {

	/**
	 * 
	 */
	@Id
	private String id;

	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private Set<String> roles;

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}