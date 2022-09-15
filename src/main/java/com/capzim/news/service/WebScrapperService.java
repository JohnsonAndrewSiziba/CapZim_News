package com.capzim.news.service;

import com.capzim.news.entity.Publication;
import com.capzim.news.webscrapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */


@Service
@RequiredArgsConstructor
@Slf4j
public class WebScrapperService {
    private final NewsdayScrapper newsdayScrapper;
    private final TheIndependentScrapper theIndependentScrapper;
    private final TheTimesScrapper theTimesScrapper;
    private final PublicationService publicationService;

    private final TheHeraldScrapper theHeraldScrapper;

    private final BloombergScrapper bloombergScrapper;

    private final SkyNewsScrapper skyNewsScrapper;

    private final TheEconomistScrapper theEconomistScrapper;

    private final TheFinancialGazetteScrapper theFinancialGazetteScrapper;

    private final TheSundayMailScrapper theSundayMailScrapper;

    private final TimeWebScrapper timeWebScrapper;

    private final TheNewYorkTimesScrapper theNewYorkTimesScrapper;

    @Async
    public void doWebScrapping(List<UUID> uuidList){
        List<Publication> publicationList = publicationService.findAll();

        for (Publication publication: publicationList){
            if (uuidList.contains(publication.getId())){
                switch (publication.getName()) {
                    case "NewsDay" -> {
                        log.info("Case Newsday");
                        newsdayScrapper.doWebScrapping(publication);
                    }
                    case "The Zimbabwe Independent" -> {
                        log.info("Case The Independent");
                        theIndependentScrapper.doWebScrapping(publication);
                    }
                    case "The Financial Gazette" -> {
                        log.info("Case The Financial Gazette");
                        theFinancialGazetteScrapper.doWebScrapping(publication);
                    }
                    case "The Sunday Mail" -> {
                        log.info("Case The Sunday Mail");
                        theSundayMailScrapper.doWebScrapping(publication);
                    }
                    case "The Herald" -> {
                        log.info("Case The Herald");
                        theHeraldScrapper.doWebScrapping(publication);
                    }
                    case "Bloomberg" -> {
                        log.info("Case Bloomberg");
                        bloombergScrapper.doWebScrapping(publication);
                    }
                    case "The Times" -> {
                        log.info("Case The Times");
                        theTimesScrapper.doWebScrapping(publication);
                    }
                    case "Sky News" -> {
                        log.info("Case Sky News");
                        skyNewsScrapper.doWebScrapping(publication);
                    }
                    case "The Economist" -> {
                        log.info("Case The Economist");
                        theEconomistScrapper.doWebScrapping(publication);
                    }
                    case "Chronicle" -> {
                        log.info("Case The Chronicle");
                        theSundayMailScrapper.doWebScrapping(publication);
                    }
                    case "Business Weekly" -> {
                        log.info("Case Business Weekly");
                        theSundayMailScrapper.doWebScrapping(publication);
                    }
                    case "Time" -> {
                        log.info("Case Time");
                        timeWebScrapper.doWebScrapping(publication);
                    }

                    case "The New York Times" -> {
                        log.info("Case The New York Times");
                        theNewYorkTimesScrapper.doWebScrapping(publication);
                    }
                    default ->
                        // No match
                            log.info("No case matched");
                }
            }
        }

    }

}