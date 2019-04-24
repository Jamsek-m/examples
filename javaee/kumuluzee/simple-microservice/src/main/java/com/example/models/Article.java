package com.example.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "articles")
@NamedQueries({
    @NamedQuery(name = Article.FIND_BY_ID, query = "SELECT a FROM Article a WHERE a.id = :id")
})
public class Article {
    
    public static final String FIND_BY_ID = "Article.findById";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    
    private double price;
    
    @Column(name = "sold_at")
    private Date soldAt;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Date getSoldAt() {
        return soldAt;
    }
    
    public void setSoldAt(Date soldAt) {
        this.soldAt = soldAt;
    }
}
