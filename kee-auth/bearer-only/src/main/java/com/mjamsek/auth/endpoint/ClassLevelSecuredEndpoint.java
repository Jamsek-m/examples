package com.mjamsek.auth.endpoint;

import com.mjamsek.auth.keycloak.annotations.AuthenticatedAllowed;
import com.mjamsek.auth.keycloak.annotations.PublicResource;
import com.mjamsek.auth.keycloak.annotations.RolesAllowed;
import com.mjamsek.auth.keycloak.annotations.SecureResource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SecureResource
@AuthenticatedAllowed
@RequestScoped
@Path("/class-level-secure")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClassLevelSecuredEndpoint {
    
    @GET
    public Response securedMethod() {
        String response = "{\"message\": \"This is class level secure endpoint! " +
            "All methods here require authentication due to class level annotation\"}";
        return Response.ok().entity(response).build();
    }
    
    @GET
    @Path("/admin")
    @RolesAllowed({"admin"})
    public Response adminOnly() {
        String response = "{\"message\": \"This method overrides class level security! " +
            "Only user with role (realm or any client) 'admin' can invoke this endpoint.\"}";
        return Response.ok().entity(response).build();
    }
    
    @GET
    @Path("/public")
    @PublicResource
    public Response publicMethod() {
        String response = "{\"message\": \"This is public endpoint!\"}";
        return Response.ok().entity(response).build();
    }
}
