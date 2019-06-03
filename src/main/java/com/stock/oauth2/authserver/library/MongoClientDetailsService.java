package com.stock.oauth2.authserver.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.stock.oauth2.authserver.document.MongoClientDetails;
import com.stock.oauth2.utill.AuthConstant;

/**
 * @author shriram
 *
 */
public class MongoClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    /**
     * 
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Query query = new Query();
        query.addCriteria(Criteria.where(AuthConstant.CLIENT_ID).is(clientId));
        MongoClientDetails clientDetails = mongoTemplate.findOne(query, MongoClientDetails.class);
        if (clientDetails == null) {
            throw new ClientRegistrationException(String.format("Client with id %s not found", clientId));
        }
        return clientDetails;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        if (loadClientByClientId(clientDetails.getClientId()) == null) {
            MongoClientDetails mongoClientDetails =
                    new MongoClientDetails(clientDetails.getClientId(), clientDetails.getResourceIds(),
                            clientDetails.isSecretRequired(), NoOpPasswordEncoder.getInstance().encode(clientDetails.getClientSecret()),
                            clientDetails.isScoped(),
                            clientDetails.getScope(), clientDetails.getAuthorizedGrantTypes(), clientDetails.getRegisteredRedirectUri(),
                            clientDetails.getAuthorities(), clientDetails.getAccessTokenValiditySeconds(),
                            clientDetails.getRefreshTokenValiditySeconds(), clientDetails.isAutoApprove("true"),
                            clientDetails.getAdditionalInformation());
            mongoTemplate.save(mongoClientDetails);
        } else {
            throw new ClientAlreadyExistsException(String.format("Client with id %s already existed",
                    clientDetails.getClientId()));
        }
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        Query query = new Query();
        query.addCriteria(Criteria.where(AuthConstant.CLIENT_ID).is(clientDetails.getClientId()));

        Update update = new Update();
        update.set(AuthConstant.RESOURCE_IDS, clientDetails.getResourceIds());
        update.set(AuthConstant.SCOPE, clientDetails.getScope());
        update.set(AuthConstant.AUTHORIZED_GRANT_TYPES, clientDetails.getAuthorizedGrantTypes());
        update.set(AuthConstant.REGISTERED_REDIRECT_URI, clientDetails.getRegisteredRedirectUri());
        update.set(AuthConstant.AUTHORITIES, clientDetails.getAuthorities());
        update.set(AuthConstant.ACCESS_TOKEN_VALIDITY_SECONDS, clientDetails.getAccessTokenValiditySeconds());
        update.set(AuthConstant.REFRESH_TOKEN_VALIDITY_SECONDS, clientDetails.getRefreshTokenValiditySeconds());
        update.set(AuthConstant.ADDITIONAL_INFORMATION, clientDetails.getAdditionalInformation());

        UpdateResult writeResult = mongoTemplate.updateFirst(query, update, MongoClientDetails.class);

        if(writeResult.getModifiedCount() <= 0) {
            throw new NoSuchClientException(String.format("Client with id %s not found", clientDetails.getClientId()));
        }
    }

    @Override
    public void updateClientSecret(String clientId, String clientSecret) throws NoSuchClientException {
        Query query = new Query();
        query.addCriteria(Criteria.where(AuthConstant.CLIENT_ID).is(clientId));

        Update update = new Update();
        update.set(AuthConstant.CLIENT_SECRET, NoOpPasswordEncoder.getInstance().encode(clientSecret));

        UpdateResult writeResult = mongoTemplate.updateFirst(query, update, MongoClientDetails.class);

        if(writeResult.getModifiedCount() <= 0) {
            throw new NoSuchClientException(String.format("Client with id %s not found", clientId));
        }
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        Query query = new Query();
        query.addCriteria(Criteria.where(AuthConstant.CLIENT_ID).is(clientId));

        DeleteResult writeResult = mongoTemplate.remove(query, MongoClientDetails.class);

        if(writeResult.getDeletedCount() <= 0) {
            throw new NoSuchClientException(String.format("Client with id %s not found", clientId));
        }
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<ClientDetails> result =  new ArrayList<ClientDetails>();
        List<MongoClientDetails> details = mongoTemplate.findAll(MongoClientDetails.class);
        for (MongoClientDetails detail : details) {
            result.add(detail);
        }
        return result;
    }
}