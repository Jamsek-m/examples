package com.mjamsek.auth.endpoint;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RequestScoped
@Path("/public")
@Produces(MediaType.APPLICATION_JSON)
public class PublicEndpoint {
    
    @GET
    public Response publicMethod() {
        String response = "{\"message\": \"This is public endpoint! All methods here are publicly accessible\"}";
        return Response.ok().entity(response).build();
    }
    
}
