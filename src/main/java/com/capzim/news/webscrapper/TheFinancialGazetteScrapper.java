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
 * Date: 15/9/2022
 * Time: 08:06
 */

@Slf4j
@Component
public class TheFinancialGazetteScrapper extends WebScrapper{

    /**
     * https://fingaz.co.zw/category/c77-companies-a-markets/
     */
    @Override
    public void getArticlesFromDocument() {
        log.info("Inside getArticlesFromDocument in TheFinancialGazetteScrapper");
        Elements articleComponents = this.getWebDocument().select("article");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting the the financial gazette articles.");

        for (Element article: articleComponents){
            try{
                String articleTitle = article.selectFirst("h3").text();
                String imageUrl = article.selectFirst("img").attr("src");
                Date articleDate  = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                String articleUrl = article.selectFirst("a").attr("href");

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
