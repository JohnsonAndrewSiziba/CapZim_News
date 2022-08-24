package com.capzim.news.schedule;

import com.capzim.news.webscrapper.NewsdayScrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class GetNewsSchedule {

    private final NewsdayScrapper newsdayScrapper;

    @Scheduled(cron="0 0 7 ? * * ", zone="CAT") //At 07:00:00am every day
    public void getNews(){
        log.info("Inside getNews of GetNewsSchedule");

        try {
            log.info("Running newsday scrapper");
            newsdayScrapper.doWebScrapping();
            newsdayScrapper.persistToDatabase();
        }
        catch (Exception e) {
            log.error("Running NewsDay scrapper failed: {}", e.getMessage());
        }
    }

}
