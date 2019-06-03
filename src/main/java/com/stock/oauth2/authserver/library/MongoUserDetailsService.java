package com.stock.oauth2.authserver.library;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Sets;
import com.stock.oauth2.authserver.config.YAMLConfig;
import com.stock.oauth2.authserver.document.MongoAccessToken;
import com.stock.oauth2.authserver.document.MongoAuthorizationCode;
import com.stock.oauth2.authserver.document.MongoClientDetails;
import com.stock.oauth2.authserver.document.MongoUser;
import com.stock.oauth2.utill.AuthConstant;

/**
 * @author shriram
 *
 */
public class MongoUserDetailsService implements UserDetailsService {

	/**
	 * 
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	YAMLConfig config;

	/**
	 *
	 * @param username the username identifying the user whose data is required.
	 *
	 * @return a fully populated user record (never <code>null</code>)
	 *
	 * @throws UsernameNotFoundException if the user could not be found or the user
	 *                                   has no GrantedAuthority
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query query = new Query();
		query.addCriteria(Criteria.where(AuthConstant.USER_NAME).is(username));
		MongoUser user = mongoTemplate.findOne(query, MongoUser.class);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Username %s not found", username));
		}

		String[] roles = new String[user.getRoles().size()];

		return new User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRoles().toArray(roles)));
	}

	@PostConstruct
	private void crate() {

		mongoTemplate.dropCollection(MongoUser.class);
		mongoTemplate.dropCollection(MongoClientDetails.class);
		mongoTemplate.dropCollection(MongoAccessToken.class);
		mongoTemplate.dropCollection(MongoAuthorizationCode.class);
		MongoUser mongoUser = new MongoUser();
		mongoUser.setUsername(config.getUserName());
		mongoUser.setPassword("{noop}"+config.getPassword());
		mongoUser.setRoles(Sets.newHashSet(config.getRoles().get(0)));
		mongoTemplate.save(mongoUser);
		MongoClientDetails clientDetails = new MongoClientDetails();
		clientDetails.setClientId(config.getClientId());
		clientDetails.setClientSecret("{noop}"+config.getClientScret());
		clientDetails.setSecretRequired(true);
		clientDetails.setResourceIds(Sets.newHashSet(config.getResourceId()));
		clientDetails.setScope(Sets.newHashSet(config.getSocpe()));
		clientDetails.setAuthorizedGrantTypes(Sets.newHashSet(config.getGrantType().get(0),
				config.getGrantType().get(1), config.getGrantType().get(2), config.getGrantType().get(3)));
		clientDetails.setRegisteredRedirectUri(Sets.newHashSet(config.getRegisterdRedirectUrl()));
		clientDetails.setAuthorities(AuthorityUtils.createAuthorityList(config.getRoles().get(1)));
		clientDetails.setAccessTokenValiditySeconds(config.getAccessTokenValidity());
		clientDetails.setRefreshTokenValiditySeconds(config.getRefreshTokenValidity());
		clientDetails.setAutoApprove(false);
		mongoTemplate.save(clientDetails);
	}
}