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
import java.util.Objects;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Date: 15/9/2022
 * Time: 08:25
 */


@Component
@Slf4j
public class TheSundayMailScrapper extends WebScrapper{
    /**
     * https://www.sundaymail.co.zw/category/business
     */
    @Override
    public void getArticlesFromDocument() {
        log.info("Inside getArticlesFromDocument of TheSundayMailScrapper");

        Elements articleComponents = this.getWebDocument().select(".hentry.sirius-card");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting the the sunday mail articles.");

        for (Element article : articleComponents){
            try {
                String articleTitle = article.selectFirst(".entry-title.list-article-title").text();
                String articleUrl = article.selectFirst("a").attr("href");
                String imageUrl = article.selectFirst("link").attr("href");
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
