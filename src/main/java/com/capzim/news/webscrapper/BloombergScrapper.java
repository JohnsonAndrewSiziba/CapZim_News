package com.capzim.news.webscrapper;

import com.capzim.news.model.WebScrapperModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Date: 14/9/2022
 * Time: 13:45
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class BloombergScrapper extends WebScrapper{
    @Override
    /*
     * Bloomberg Url: https://www.bloomberg.com/markets
     */
    public void getArticlesFromDocument() {
        System.out.println(this.getWebDocument());
        Element articlesContainer = this.getWebDocument().selectFirst(".story-package-module__stories");
        System.out.println(articlesContainer);

        Elements articleComponents = this.getWebDocument().select(".story-package-module__stories").select("article");

        List<WebScrapperModel> tempList = new ArrayList<>();

        log.info("Getting Bloomberg Articles.");

        for(Element article: articleComponents){

            System.out.println(article);
            System.out.println("==============================================");
        }
    }
}
