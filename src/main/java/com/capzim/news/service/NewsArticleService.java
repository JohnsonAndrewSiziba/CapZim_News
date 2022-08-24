package com.capzim.news.service;

import com.capzim.news.entity.NewsArticle;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

public interface NewsArticleService {
    public List<NewsArticle> getAllArticles();

    public List<NewsArticle> getArticlesByDate(Date date);

    public List<NewsArticle> getTodayArticles();

    public NewsArticle saveNewsArticle(NewsArticle newsArticle);

    public void deleteNewsArticle(NewsArticle newsArticle);

    public NewsArticle getArticleById(UUID id);
}
