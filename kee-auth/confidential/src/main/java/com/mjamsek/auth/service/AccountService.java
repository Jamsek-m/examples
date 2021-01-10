package com.mjamsek.auth.service;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import com.mjamsek.auth.keycloak.client.KeycloakClient;
import com.mjamsek.auth.keycloak.exceptions.KeycloakException;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import java.net.URI;

@ApplicationScoped
public class AccountService {
    
    private AccountAPI api;
    
    @PostConstruct
    private void init() {
        api = ConfigurationUtil.getInstance().get("keycloak.auth-server-url").map(keycloakUrl -> {
            return RestClientBuilder
                .newBuilder()
                .baseUri(URI.create(keycloakUrl))
                .build(AccountAPI.class);
        }).orElseThrow();
    }
    
    public JsonArray getAccounts() {
        try {
            String realm = ConfigurationUtil.getInstance().get("keycloak.realm").orElseThrow();
            return KeycloakClient.callKeycloak((token) -> {
                return api.getAccounts(realm, "Bearer " + token);
            });
        } catch (KeycloakException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
