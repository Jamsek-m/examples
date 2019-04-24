package com.example.endpoints;


import com.example.models.Article;
import com.example.services.ArticleService;
import com.kumuluz.ee.rest.beans.QueryParameters;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("/articles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleEndpoint {
    
    @Context
    protected UriInfo uriInfo;
    
    @Inject
    private ArticleService articleService;
    
    @GET
    public Response getAll() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Article> articles = articleService.getAll(queryParameters);
        return Response.ok(articles).build();
    }
    
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Article article = articleService.getById(id);
        if (article == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(article).build();
    }
    
    @POST
    public Response createArticle(Article article) {
        Article createdArticle = articleService.addArticle(article);
        return Response.ok(createdArticle).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response updateArticle(@PathParam("id") long id, Article article) {
        Article updatedArticle = articleService.updateArticle(article, id);
        return Response.ok(updatedArticle).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") long id) {
        articleService.deleteArticle(id);
        return Response.noContent().build();
    }
}
