package com.mjamsek.auth.service;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterProvider(NoRoleExceptionMapper.class)
public interface AccountAPI {
    
    @GET
    @Path("/admin/realms/{realm}/users")
    JsonArray getAccounts(
        @PathParam("realm") String realm,
        @HeaderParam("Authorization") String authorizationHeader
    );
    
}
