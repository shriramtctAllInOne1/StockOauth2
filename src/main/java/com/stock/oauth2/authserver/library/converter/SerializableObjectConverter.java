package com.stock.oauth2.authserver.library.converter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.util.SerializationUtils;

/**
 * @author shriram
 *
 */
public class SerializableObjectConverter {

    /**
     * @param object
     * @return
     */
    public static String serialize(OAuth2Authentication object) {
        byte[] bytes = SerializationUtils.serialize(object);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * @param encodedObject
     * @return
     */
    public static OAuth2Authentication deserialize(String encodedObject) {
        byte[] bytes = Base64.decodeBase64(encodedObject);
        return (OAuth2Authentication) SerializationUtils.deserialize(bytes);
    }

}