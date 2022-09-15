package com.capzim.news.webscrapper;

import com.capzim.news.model.FileModel;
import com.capzim.news.model.WebScrapperModel;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Date: 14/9/2022
 * Time: 15:43
 */


@Component
@Slf4j
public class TheEconomistScrapper extends WebScrapper{
    @Override
    /**
     * https://www.economist.com/
     */
    public void getArticlesFromDocument() {
        Element articlesContainer = this.getWebDocument().selectFirst(".css-1gudbqu.e1mrg8dy0");

        Elements articlesList = articlesContainer.select(".css-17glo8i.e1mrg8dy0");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting the economist articles.");

        for (Element article: articlesList){
            try{
                String articleTitle = article.selectFirst("h3").text();
                String articleUrl = "https://www.economist.com/" + article.selectFirst("a").attr("href");
                String imageUrl = article.selectFirst("meta").attr("content");
                Date articleDate  = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                FileModel fileModel = this.readUrlToByteArray(imageUrl);


                WebScrapperModel webScrapperModel = new WebScrapperModel();
                webScrapperModel.setTitle(articleTitle);
                webScrapperModel.setUrl(articleUrl);
                webScrapperModel.setImageUrl(imageUrl);
                webScrapperModel.setDate(articleDate);
                webScrapperModel.setFileModel(fileModel);

                tempList.add(webScrapperModel);
            }
            catch (Exception e){
                log.error(e.getMessage());
            }
        }
        this.setWebScrapperModelList(tempList);
    }
}
