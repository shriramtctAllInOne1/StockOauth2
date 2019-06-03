package com.stock.oauth2.authserver.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "constant")
public class YAMLConfig {

	/**
	 * 
	 */
	String tokenUrl;
	/**
	 * 
	 */
	String clientId;
	/**
	 * 
	 */
	String clientScret;
	/**
	 * 
	 */
	String resourceId;
	/**
	 * 
	 */
	List<String> socpe;
	/**
	 * 
	 */
	String userName;

	/**
	 * 
	 */
	String password;
	/**
	 * 
	 */
	List<String> grantType;

	/**
	 * 
	 */
	Integer accessTokenValidity;
	/**
	 * 
	 */
	Integer refreshTokenValidity;

	/**
	 * 
	 */
	String registerdRedirectUrl;

	/**
	 * 
	 */
	List<String> roles;

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientScret() {
		return clientScret;
	}

	public void setClientScret(String clientScret) {
		this.clientScret = clientScret;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getGrantType() {
		return grantType;
	}

	public void setGrantType(List<String> grantTypeList) {
		this.grantType = grantTypeList;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getRegisterdRedirectUrl() {
		return registerdRedirectUrl;
	}

	public void setRegisterdRedirectUrl(String registerdRedirectUrl) {
		this.registerdRedirectUrl = registerdRedirectUrl;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getSocpe() {
		return socpe;
	}

	public void setSocpe(List<String> socpe) {
		this.socpe = socpe;
	}

	@PostConstruct
	@Override
	public String toString() {
		String str= "YAMLConfig [tokenUrl=" + tokenUrl + ", clientId=" + clientId + ", clientScret=" + clientScret
				+ ", resourceId=" + resourceId + ", socpe=" + socpe + ", userName=" + userName + ", password="
				+ password + ", grantType=" + grantType + ", accessTokenValidity=" + accessTokenValidity
				+ ", refreshTokenValidity=" + refreshTokenValidity + ", registerdRedirectUrl=" + registerdRedirectUrl
				+ ", roles=" + roles + "]";
		System.out.println(str);
		return str;
	}

}
