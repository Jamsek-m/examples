package com.mjamsek.auth.service;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.mjamsek.auth.keycloak.exceptions.KeycloakException;
import com.mjamsek.auth.keycloak.services.KeycloakClient;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import java.net.URI;
import java.util.logging.Logger;

@ApplicationScoped
public class AccountService {
    
    private static final Logger log = Logger.getLogger(AccountService.class.getName());
    
    private AccountAPI api;
    
    private String realm;
    
    @PostConstruct
    private void init() {
        this.realm = ConfigurationUtil.getInstance().get("kc.realm").orElse("not-set");
        String serverUrl = ConfigurationUtil.getInstance().get("kc.auth-server-url").orElse("not-set");
        api = RestClientBuilder.newBuilder()
            .baseUri(URI.create(serverUrl))
            .build(AccountAPI.class);
    }
    
    public JsonArray getAccounts() {
        try {
            JsonArray resultArray = KeycloakClient.callKeycloak((token) -> api.getAccounts(realm, "Bearer " + token));
            log.info("Received response array of size: " + resultArray.size());
            return resultArray;
        } catch (KeycloakException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
