package com.stock.oauth2.authserver.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.stock.oauth2.authserver.library.converter.SerializableObjectConverter;

/**
 * @author shriram
 *
 */
@Document(collection = "access_token")
public class MongoAccessToken {

	/**
	 * 
	 */
	@Id
	private String id;

	/**
	 * 
	 */
	private String tokenId;
	/**
	 * 
	 */
	private OAuth2AccessToken token;
	/**
	 * 
	 */
	private String authenticationId;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String clientId;
	/**
	 * 
	 */
	private String authentication;
	/**
	 * 
	 */
	private String refreshToken;

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
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return
	 */
	public OAuth2AccessToken getToken() {
		return token;
	}

	/**
	 * @param token
	 */
	public void setToken(OAuth2AccessToken token) {
		this.token = token;
	}

	/**
	 * @return
	 */
	public String getAuthenticationId() {
		return authenticationId;
	}

	/**
	 * @param authenticationId
	 */
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
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
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return
	 */
	public OAuth2Authentication getAuthentication() {
		return SerializableObjectConverter.deserialize(authentication);
	}

	/**
	 * @param authentication
	 */
	public void setAuthentication(OAuth2Authentication authentication) {
		this.authentication = SerializableObjectConverter.serialize(authentication);
	}

	/**
	 * @return
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}