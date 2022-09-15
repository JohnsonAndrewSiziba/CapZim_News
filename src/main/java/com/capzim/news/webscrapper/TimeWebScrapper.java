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
 * Time: 09:14
 */


@Component
@Slf4j
public class TimeWebScrapper extends WebScrapper{

    /*
     * https://time.com/section/business/
     */
    @Override
    public void getArticlesFromDocument() {
        log.info("Inside getArticlesFromDocument of TimeWebScrapper");

        Elements articleComponents = this.getWebDocument().select(".taxonomy-tout");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting Time articles.");

        for (Element article : articleComponents){
            try {
                String articleTitle = article.selectFirst("h2").text();
                String articleUrl =  "https://time.com" + article.selectFirst("a").attr("href");

                String imageUrl = article.selectFirst("img").attr("src").split(" ")[0];

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
