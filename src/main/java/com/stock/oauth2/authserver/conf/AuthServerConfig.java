package com.stock.oauth2.authserver.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.stock.oauth2.authserver.config.YAMLConfig;
import com.stock.oauth2.authserver.library.MongoAuthorizationCodeServices;
import com.stock.oauth2.authserver.library.MongoClientDetailsService;
import com.stock.oauth2.utill.AuthConstant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Auth Server configuration
 * 
 * @author shriram
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	/**
	 * Authentication Manager
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	/**
	 * Token store
	 */
	@Autowired
	private TokenStore tokenStore;
	/**
	 * AccesToken converter
	 */
	@Autowired(required = false)
	private JwtAccessTokenConverter accessTokenConverter;
	
	/**
	 * 
	 */
	@Autowired
	YAMLConfig config;

	/**
	 * 
	 * client Details
	 * 
	 * @return
	 */
	@Bean
	public MongoClientDetailsService clientDetailsService1() {
		return new MongoClientDetailsService();
	}

	/**
	 * 
	 * Authorization code
	 * 
	 * @return
	 */
	@Bean
	public AuthorizationCodeServices authorizationCodeServices() {
		return new MongoAuthorizationCodeServices();
	}

	/**
	 * 
	 * ClientDeatils configuration
	 * 
	 * @return
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService1());
	}

	/**
	 * 
	 * AuthorizationServerEndpointsConfigurer
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authorizationCodeServices(authorizationCodeServices()).tokenServices(tokenServices())
				.authenticationManager(authenticationManager);
	}

	/**
	 * 
	 * AuthorizationServerSecurityConfigurer
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess(AuthConstant.PERMIT_ALL).checkTokenAccess(config.getSocpe().get(2));
		oauthServer.allowFormAuthenticationForClients();
	}

	/**
	 * 
	 * Token Enhancer 
	 * @return
	 */
	@Primary
	@Bean
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenStore(tokenStore);
		List<TokenEnhancer> enhancers = new ArrayList<>();
		if (accessTokenConverter != null) {
			enhancers.add(accessTokenConverter);
		}
		enhancers.add(new TokenEnhancer() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				final Authentication userAuthentication = authentication.getUserAuthentication();
				final DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
				Set<String> existingScopes = new HashSet<>();
				existingScopes.addAll(defaultOAuth2AccessToken.getScope());
				if (userAuthentication != null) {
					existingScopes.add(config.getSocpe().get(0));
				} else {
					existingScopes.add(config.getSocpe().get(2));
				}
				defaultOAuth2AccessToken.setScope(existingScopes);
				return defaultOAuth2AccessToken;
			}
		});
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(enhancers);
		tokenServices.setTokenEnhancer(enhancerChain);
		return tokenServices;
	}
	
	
}
