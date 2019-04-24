package com.example.services;

import com.example.models.Article;
import com.kumuluz.ee.rest.beans.QueryParameters;

import java.util.List;

public interface ArticleService {
    
    List<Article> getAll(QueryParameters queryParameters);
    
    Article getById(long id);
    
    Article addArticle(Article article);
    
    Article updateArticle(Article article, long id);
    
    void deleteArticle(long id);
}
