package com.stock.oauth2.authserver.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author shriram
 *
 */
@Document(collection = "client_details")
public class MongoClientDetails implements ClientDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    /**
     * 
     */
    public MongoClientDetails() {

    }

    /**
     * @param clientId
     * @param resourceIds
     * @param secretRequired
     * @param clientSecret
     * @param scoped
     * @param scope
     * @param authorizedGrantTypes
     * @param registeredRedirectUri
     * @param authorities
     * @param accessTokenValiditySeconds
     * @param refreshTokenValiditySeconds
     * @param autoApprove
     * @param additionalInformation
     */
    public MongoClientDetails(String clientId, Set<String> resourceIds, boolean secretRequired, String clientSecret,
                              boolean scoped, Set<String> scope, Set<String> authorizedGrantTypes,
                              Set<String> registeredRedirectUri, Collection<GrantedAuthority> authorities,
                              Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds,
                              boolean autoApprove, Map<String, Object> additionalInformation) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secretRequired = secretRequired;
        this.clientSecret = clientSecret;
        this.scoped = scoped;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUri = registeredRedirectUri;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.autoApprove = autoApprove;
        this.additionalInformation = additionalInformation;
    }

    /**
     * 
     */
    @Id
    private String id;

    /**
     * 
     */
    private String clientId;
    /**
     * 
     */
    private Set<String> resourceIds;
    /**
     * 
     */
    private boolean secretRequired;
    /**
     * 
     */
    private String clientSecret;
    /**
     * 
     */
    private boolean scoped;
    /**
     * 
     */
    private Set<String> scope;
    /**
     * 
     */
    private Set<String> authorizedGrantTypes;
    /**
     * 
     */
    private Set<String> registeredRedirectUri;
    /**
     * 
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 
     */
    private Integer accessTokenValiditySeconds;
    /**
     * 
     */
    private Integer refreshTokenValiditySeconds;
    /**
     * 
     */
    private boolean autoApprove;
    /**
     * 
     */
    private Map<String, Object> additionalInformation;

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
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @param resourceIds
     */
    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * @param secretRequired
     */
    public void setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    /**
     * @param clientSecret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @param scoped
     */
    public void setScoped(boolean scoped) {
        this.scoped = scoped;
    }

    /**
     * @param scope
     */
    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    /**
     * @param authorizedGrantTypes
     */
    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * @param registeredRedirectUri
     */
    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    /**
     * @param authorities
     */
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * @param accessTokenValiditySeconds
     */
    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    /**
     * @param refreshTokenValiditySeconds
     */
    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    /**
     * @return
     */
    public boolean isAutoApprove() {
        return autoApprove;
    }

    /**
     * @param autoApprove
     */
    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    /**
     * @param additionalInformation
     */
    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return secretRequired;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return scoped;
    }

    @Override
    public Set<String> getScope() {
        return scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }
}