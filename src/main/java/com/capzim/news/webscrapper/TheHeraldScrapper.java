package com.capzim.news.webscrapper;

import com.capzim.news.model.FileModel;
import com.capzim.news.model.WebScrapperModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Date: 14/9/2022
 * Time: 08:38
 */


@Component
@RequiredArgsConstructor
@Slf4j
public class TheHeraldScrapper extends  WebScrapper{


    /**
     The Herald Url: https://www.herald.co.zw/category/articles/business/
     */
    @SneakyThrows
    @Override
    public void getArticlesFromDocument() {

        Elements articleComponents = this.getWebDocument().select(".hentry.sirius-card");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting the herald articles.");

        for (Element article : articleComponents){
            String articleTitle = Objects.requireNonNull(article.selectFirst(".entry-title.list-article-title")).text();
            String articleUrl = Objects.requireNonNull(article.selectFirst("a")).attr("href");
            String imageUrl = Objects.requireNonNull(article.selectFirst("link")).attr("href");
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

        this.setWebScrapperModelList(tempList);

    }
}
