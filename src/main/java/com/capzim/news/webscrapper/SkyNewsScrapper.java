package com.capzim.news.webscrapper;

import com.capzim.news.model.FileModel;
import com.capzim.news.model.WebScrapperModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Date: 14/9/2022
 * Time: 15:03
 */


@Component
@Slf4j
public class SkyNewsScrapper extends WebScrapper{
    @SneakyThrows
    @Override
    /*
     * Sky News: https://news.sky.com/business
     */
    public void getArticlesFromDocument()  {
        Element articlesContainer = this.getWebDocument().selectFirst(".sdc-site-tiles");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting sky news articles.");

        Elements articleElements = articlesContainer.select(".sdc-site-tiles__item.sdc-site-tile.glints-box.glints-box-hover.glints-box--mobile-edge.sdc-site-tile--has-link");

        for (Element article: articleElements){
            try {
                String articleTitle = article.selectFirst("h3").text();
                String articleUrl = "https://news.sky.com" + article.selectFirst("a").attr("href");
                String imageUrl = article.selectFirst("source").attr("srcset").split(" ")[0];

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
