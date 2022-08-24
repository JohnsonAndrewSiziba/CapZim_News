package com.capzim.news.controller;

import com.capzim.news.model.NewsArticleRequestModel;
import com.capzim.news.entity.NewsArticle;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */


public interface NewsController {


    public List<NewsArticle> getAllArticles();


    public NewsArticle saveNewsArticle(NewsArticleRequestModel newsArticle);


    public NewsArticle getArticleById(UUID id);


    public List<NewsArticle> getArticlesByDate(Date date);


    public List<NewsArticle> getCurrentArticles();


    public void  deleteNewsArticle(UUID id);


    public NewsArticle editNewsArticle(NewsArticle newsArticle, UUID id);


}
