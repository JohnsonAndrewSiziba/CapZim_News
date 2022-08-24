package com.capzim.news.webscrapper;
import com.capzim.news.model.WebScrapperModel;
import com.capzim.news.service.PublicationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    private final PublicationServiceImpl publicationService;

    @Override
    public void doWebScrapping() {
        this.setPublication(publicationService.findPublicationByName("Newsday"));
        this.setScrappingUrl(getPublication().getHomeUrl());

        Document webDocument;

        try {
            log.info("Inside doWebScrapping of NewsdayScrapper.");
            log.info("Getting web document.");

            webDocument = Jsoup.connect(this.getScrappingUrl()).get();

        } catch (Exception e) {
            log.error("Unable to load page, {}. Error: {}", this.getScrappingUrl(), e.getMessage());
            return;
        }

        Elements articleComponents = webDocument.select(".td-module-container.td-category-pos-above");

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
