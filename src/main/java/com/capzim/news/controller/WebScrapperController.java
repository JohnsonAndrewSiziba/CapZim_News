package com.capzim.news.controller;

import com.capzim.news.model.WebScrapperOptionsModel;
import com.capzim.news.webscrapper.NewsdayScrapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */


@RestController
@RequestMapping("/api/v1/news/web_scrapper")
@RequiredArgsConstructor
@Slf4j
public class WebScrapperController {

    private final NewsdayScrapper newsdayScrapper;


    /**
     * Run news web-scrapper
     */
    @PostMapping("/")
    @Operation(summary = "Run news web scrapper")
    public String doWebScrapping(@RequestBody WebScrapperOptionsModel optionsModel){
        log.info("Inside doWebScrapping of WebScrapperController");
        if (optionsModel.isNewsDay()){

            log.info("News day web scrapper");
            newsdayScrapper.doWebScrapping();
            newsdayScrapper.persistToDatabase();
        }
        return "Done";
    }
}
