package com.example.services.impl;

import com.example.models.Article;
import com.example.services.ArticleService;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ArticleServiceImpl implements ArticleService {
    
    @PersistenceContext(unitName = "main-jpa-unit")
    private EntityManager em;
    
    @Override
    public List<Article> getAll(QueryParameters queryParameters) {
        return JPAUtils.queryEntities(em, Article.class, queryParameters);
    }
    
    @Override
    public Article getById(long id) {
        TypedQuery<Article> query = em.createNamedQuery(Article.FIND_BY_ID, Article.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch(NoResultException exc) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public Article addArticle(Article article) {
        em.persist(article);
        return article;
    }
    
    @Override
    @Transactional
    public Article updateArticle(Article article, long id) {
        Article oldArticle = this.getById(id);
        if (oldArticle != null) {
            oldArticle.setName(article.getName());
            oldArticle.setPrice(article.getPrice());
            oldArticle.setSoldAt(article.getSoldAt());
            em.merge(oldArticle);
            return oldArticle;
        } else {
            throw new RuntimeException("Article not found!");
        }
    }
    
    @Override
    @Transactional
    public void deleteArticle(long id) {
        Article article = this.getById(id);
        if (article != null) {
            em.remove(article);
        }
    }
}
