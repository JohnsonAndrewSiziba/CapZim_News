package com.capzim.news.webscrapper;

import com.capzim.news.model.FileModel;
import com.capzim.news.model.WebScrapperModel;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Component
@Slf4j
public class TheTimesScrapper extends WebScrapper{


    /**
     * The times url: https://www.thetimes.co.uk/business
     */
    @Override
    public void getArticlesFromDocument() {
        Elements articleComponents = this.getWebDocument().select("article");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting the times articles.");

        for(Element article: articleComponents){

            try{
                String articleUrl = article.selectFirst("a").attr("href");
                String articleTitle = article.selectFirst("h3").text();
                Date articleDate  = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                String imageUrl = "https://www.thetimes.co.uk/d/img/icons/icon_1_5x-a13ee0bf9a.png";

                FileModel fileModel = this.readUrlToByteArray(imageUrl);


                WebScrapperModel webScrapperModel = new WebScrapperModel();
                webScrapperModel.setTitle(articleTitle);
                webScrapperModel.setUrl(articleUrl);
                webScrapperModel.setImageUrl(imageUrl);
                webScrapperModel.setDate(articleDate);
                webScrapperModel.setFileModel(fileModel);

                tempList.add(webScrapperModel);

            } catch (Exception e){
                log.error(e.getMessage());
            }

        }
        this.setWebScrapperModelList(tempList);
    }
}
