package com.mjamsek.auth.endpoint;

import com.mjamsek.auth.keycloak.annotations.*;
import com.mjamsek.auth.keycloak.context.AuthContext;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@SecureResource
@RequestScoped
@Path("/secure")
@Produces(MediaType.APPLICATION_JSON)
public class SecuredEndpoint {
    
    private static final Logger LOG = Logger.getLogger(SecuredEndpoint.class.getName());
    
    @Inject
    private AuthContext authContext;
    
    @GET
    @ClientRolesAllowed(client = "confidential-client", roles = {"admin", "moderator"})
    public Response clientAdminOrModMethod() {
        
        LOG.log(
            Level.INFO,
            "Endpoint invoked by user with username {0} and id {1}",
            new String[]{authContext.getUsername(), authContext.getId()}
        );
        
        return Response.ok().build();
    }
    
    @GET
    @Path("/realm-role")
    @RealmRolesAllowed({"admin"})
    public Response realmAdminMethod() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/any-role")
    @RolesAllowed({"admin"})
    public Response anyAdminMethod() {
        return Response.ok().build();
    }
    
    @GET
    @Path("/authenticated")
    @AuthenticatedAllowed
    public Response authenticatedMethod() {
        return Response.ok().build();
    }
    
}
