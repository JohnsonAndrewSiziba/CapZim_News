package com.capzim.news.controller;

import com.capzim.news.model.NewsArticleRequestModel;
import com.capzim.news.entity.NewsArticle;
import com.capzim.news.entity.Publication;
import com.capzim.news.service.NewsArticleServiceImpl;
import com.capzim.news.service.PublicationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;


/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/news_articles")
@Slf4j
public class NewsControllerImpl implements NewsController{

    private final NewsArticleServiceImpl newsArticleService;
    private final PublicationServiceImpl publicationService;


    /**
     * Get All Saved Articles
     */
    @Override
    @GetMapping("/")
    @Operation(summary = "Get All Saved Articles")
    public List<NewsArticle> getAllArticles() {
        log.info("Inside getAllArticles of NewsController.");

        return newsArticleService.getAllArticles();
    }


    /**
     * Save New Article
     */
    @Override
    @PostMapping("/")
    @Operation(summary = "Save New Article")
    public NewsArticle saveNewsArticle(@RequestBody NewsArticleRequestModel newsArticleRequestModel) {
        log.info("Inside saveNewsArticle of NewsController.");

        Publication publication = publicationService.findPublicationById(newsArticleRequestModel.getPublicationId());


        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setPublication(publication);
        newsArticle.setTitle(newsArticleRequestModel.getTitle());
        newsArticle.setDate(newsArticleRequestModel.getDate());
        newsArticle.setUrl(newsArticleRequestModel.getUrl());
        newsArticle.setImageUrl(newsArticleRequestModel.getImageUrl());
        newsArticle.setArticleContent(newsArticleRequestModel.getArticleContent());

        return newsArticleService.saveNewsArticle(newsArticle);

    }


    /**
     * Get Article By Id
     */
    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get Article By Id")
    public NewsArticle getArticleById(@PathVariable UUID id) {
        log.info("Inside getArticleById of NewsController. ID: {}", id.toString());

        return newsArticleService.getArticleById(id);
    }


    /**
     * Get Articles By Date
     */
    @Override
    @GetMapping("/date/{date}")
    @Operation(summary = "Get Articles By Date")
    public List<NewsArticle> getArticlesByDate(@PathVariable Date date) {
        log.info("Inside getArticlesByDate of NewsController. Date: {}", date.toString());

        return newsArticleService.getArticlesByDate(date);
    }


    /**
     * Get All Current Articles
     */
    @Override
    @GetMapping("/currentArticles")
    @Operation(summary = "Get All Current Articles")
    public List<NewsArticle> getCurrentArticles() {
        log.info("Inside getCurrentArticles of NewsController.");

        return newsArticleService.getTodayArticles();
    }


    /**
     * Delete Saved Article from Database
     */
    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Saved Article from Database")
    public void deleteNewsArticle(@PathVariable UUID id) {
        log.info("Inside deleteNewsArticle of NewsController. ID: {}", id.toString());

        NewsArticle article = newsArticleService.getArticleById(id);
        newsArticleService.deleteNewsArticle(article);
    }


    /**
     * Edit Saved Article
     */
    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Edit Saved Article")
    public NewsArticle editNewsArticle(@RequestBody NewsArticle newsArticle,@PathVariable UUID id) {
        log.info("Inside editNewsArticle of NewsController. ID: {}", id.toString());

        NewsArticle savedArticle = newsArticleService.getArticleById(id);

        savedArticle.setArticleContent(newsArticle.getArticleContent());
        savedArticle.setTitle(newsArticle.getTitle());
        savedArticle.setDate(newsArticle.getDate());
        savedArticle.setUrl(newsArticle.getUrl());
        savedArticle.setImageUrl(newsArticle.getImageUrl());
        savedArticle.setArticleContent(newsArticle.getArticleContent());
        savedArticle.setPublication(newsArticle.getPublication());
        newsArticleService.saveNewsArticle(savedArticle);

        return savedArticle;
    }
}