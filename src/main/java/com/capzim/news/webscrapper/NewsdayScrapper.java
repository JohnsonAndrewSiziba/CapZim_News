package com.capzim.news.webscrapper;
import com.capzim.news.model.WebScrapperModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class NewsdayScrapper extends  WebScrapper{



    /**
    Newsday Url: https://www.newsday.co.zw/category/business/
     */
    @Override
    public void getArticlesFromDocument() {
        Elements articleComponents = this.getWebDocument().select(".td-module-container.td-category-pos-above");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting news-day articles.");
        for (Element article : articleComponents){
            String articleTitle = Objects.requireNonNull(article.selectFirst(".entry-title.td-module-title")).text();
            String articleUrl = Objects.requireNonNull(article.selectFirst("a")).attr("href");
            String imageUrl = Objects.requireNonNull(article.selectFirst(".entry-thumb.td-thumb-css")).attr("data-img-url");
            Date articleDate = Date.valueOf(
                    Objects.requireNonNull(article.selectFirst(".entry-date"))
                    .attr("datetime")
                    .split("T")[0]
            );

            WebScrapperModel webScrapperModel = new WebScrapperModel();
            webScrapperModel.setTitle(articleTitle);
            webScrapperModel.setUrl(articleUrl);
            webScrapperModel.setImageUrl(imageUrl);
            webScrapperModel.setDate(articleDate);

            tempList.add(webScrapperModel);
        }

        this.setWebScrapperModelList(tempList);
    }
}
