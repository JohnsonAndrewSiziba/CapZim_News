package com.capzim.news.webscrapper;

import com.capzim.news.entity.NewsArticle;
import com.capzim.news.entity.Publication;
import com.capzim.news.model.WebScrapperModel;
import com.capzim.news.service.NewsArticleServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Data
@AllArgsConstructor
@Component
@NoArgsConstructor
public abstract class WebScrapper {
    private String scrappingUrl;
    private List<WebScrapperModel> webScrapperModelList = new ArrayList<>();
    private Publication publication;
    @Autowired
    private NewsArticleServiceImpl newsArticleService;
    abstract public void doWebScrapping();

    public void persistToDatabase(){
        for (WebScrapperModel webScrapperModel : this.webScrapperModelList){
            NewsArticle newsArticle = webScrapperModel.generateNewsArticle(publication);

            NewsArticle existTestArticle = newsArticleService.findNewsArticleByUrl(newsArticle.getUrl());

            if (existTestArticle == null){
                newsArticleService.saveNewsArticle(newsArticle);
            }
        }
    }
}
