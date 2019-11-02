package com.mjamsek.auth.endpoint;

import com.mjamsek.auth.keycloak.annotations.*;
import com.mjamsek.auth.service.AccountService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SecureResource
@AuthenticatedAllowed
@RequestScoped
@Path("/secure")
@Produces(MediaType.APPLICATION_JSON)
public class SecureEndpoint {
    
    @Inject
    private AccountService accountService;
    
    @GET
    @ClientRolesAllowed(client = "keycloak-lib-client", roles = {"salesman", "admin"})
    public Response getAccounts() {
        JsonArray accounts = accountService.getAccounts();
        if (accounts == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(accounts).build();
    }
    
    @GET
    @Path("/public")
    @PublicResource
    public Response publicMethod() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/realm")
    @RealmRolesAllowed({"admin"})
    public Response realmAdminMethod() {
        return Response.ok().build();
    }
    
}
