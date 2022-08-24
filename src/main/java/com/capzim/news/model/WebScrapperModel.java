package com.capzim.news.model;

import com.capzim.news.entity.NewsArticle;
import com.capzim.news.entity.Publication;
import lombok.Data;

import java.sql.Date;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Data
public class WebScrapperModel {
    private String title;
    private String extract;
    private String imageUrl;
    private String url;

    private Date date;


    public NewsArticle generateNewsArticle(Publication publication){
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setTitle(this.title);
        newsArticle.setExtract(this.extract);
        newsArticle.setImageUrl(this.imageUrl);
        newsArticle.setPublication(publication);
        newsArticle.setDate(this.date);
        newsArticle.setUrl(this.url);

        return newsArticle;
    }
}
