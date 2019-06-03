package com.stock.oauth2.authserver.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.stock.oauth2.authserver.library.converter.SerializableObjectConverter;

/**
 * @author shriram
 *
 */
@Document(collection = "refresh_token")
public class MongoRefreshToken {

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
	private OAuth2RefreshToken token;
	/**
	 * 
	 */
	private String authentication;

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
	public OAuth2RefreshToken getToken() {
		return token;
	}

	/**
	 * @param token
	 */
	public void setToken(OAuth2RefreshToken token) {
		this.token = token;
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
}