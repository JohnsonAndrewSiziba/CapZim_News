package com.capzim.news.controller;

import com.capzim.news.entity.Publication;
import com.capzim.news.service.PublicationService;
import com.capzim.news.service.WebScrapperService;
import com.capzim.news.webscrapper.NewsdayScrapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */


@RestController
@RequestMapping("/api/v1/news/web_scrapper")
@RequiredArgsConstructor
@Slf4j
public class WebScrapperController {

   private final WebScrapperService webScrapperService;
   private final PublicationService publicationService;


    /**
     * Run news web-scrapper
     */
    @PostMapping("/scrape")
    @Operation(summary = "Run news web scrapper")
    public String doWebScrapping(@RequestBody List<UUID> uuidList){
        log.info("Inside doWebScrapping of WebScrapperController");

        if (uuidList.isEmpty()){
            List<Publication> publicationList = publicationService.findAll();

             uuidList = new ArrayList<>();

            for (Publication publication: publicationList){
                uuidList.add(publication.getId());
            }
        }

        webScrapperService.doWebScrapping(uuidList);
        return "Done";
    }
}
