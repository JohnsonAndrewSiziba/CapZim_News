package com.capzim.news.webscrapper;

import com.capzim.news.entity.NewsArticle;
import com.capzim.news.entity.Publication;
import com.capzim.news.model.FileModel;
import com.capzim.news.model.WebScrapperModel;
import com.capzim.news.service.NewsArticleServiceImpl;
import com.capzim.news.service.PublicationServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
@Slf4j
public abstract class WebScrapper {


    @Autowired
    private  PublicationServiceImpl publicationService;


    private String scrappingUrl;


    private List<WebScrapperModel> webScrapperModelList = new ArrayList<>();


    private Publication publication;


    Document webDocument;


    @Autowired
    private NewsArticleServiceImpl newsArticleService;


    abstract public void getArticlesFromDocument();


    public void initialize(Publication pub){
        this.setPublication(pub);

        try {
            log.info("Inside doWebScrapping of: {}", pub.getName());
            log.info("Getting web document.");

            this.webDocument = Jsoup.connect(this.getPublication().getHomeUrl()).get();

        } catch (Exception e) {
            log.error("Unable to load page, {}. Error: {}", this.getScrappingUrl(), e.getMessage());
        }
    }


    public void doWebScrapping(Publication pub){
        try {
            log.info("Inside doWebScrapping inside abstract class WebScrapper for publication with id: {}", pub.getName());

            log.info("Running initialize inside doWebScrapping");
            this.initialize(pub);
            log.info("Initialization successful.");

            log.info("Running getArticlesFromDocument inside doWebScrapping");
            this.getArticlesFromDocument();
            log.info("getArticlesFromDocument successful");

            log.info("Running persistToDatabase inside doWebScrapping");
            this.persistToDatabase();
            log.info("persistToDatabase successful");
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }


    public void persistToDatabase(){
        for (WebScrapperModel webScrapperModel : this.webScrapperModelList){
            NewsArticle newsArticle = webScrapperModel.generateNewsArticle(publication);

            NewsArticle existTestArticle = newsArticleService.findNewsArticleByUrl(newsArticle.getUrl());

            if (existTestArticle == null){
                newsArticleService.saveNewsArticle(newsArticle);
            }
        }
    }

    public FileModel readUrlToByteArray(String urlString) throws IOException {
        URL url = new URL(urlString);
        try (InputStream is = url.openStream()) {

            byte[] fileData = IOUtils.toByteArray(is);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            String contentType = connection.getContentType();

            FileModel fileModel = new FileModel();
            fileModel.setFileData(fileData);
            fileModel.setContentType(contentType);

            return fileModel;
        } catch (IOException e) {
            System.err.printf("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            log.error("Failed while reading bytes from {}: {}", url.toExternalForm(), e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
