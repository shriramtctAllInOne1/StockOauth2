package com.stock.oauth2.authserver.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.stock.oauth2.authserver.library.converter.SerializableObjectConverter;

/**
 * @author shriram
 *
 */
@Document(collection = "authorization_code")
public class MongoAuthorizationCode {

    /**
     * 
     */
    public static final String CODE = "code";

    /**
     * 
     */
    @Id
    private String id;

    /**
     * 
     */
    private String code;
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
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
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
        this.authentication = SerializableObjectConverter.serialize(authentication);;
    }
}