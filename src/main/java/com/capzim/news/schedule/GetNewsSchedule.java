package com.capzim.news.schedule;

import com.capzim.news.entity.Publication;
import com.capzim.news.service.PublicationService;
import com.capzim.news.service.WebScrapperService;
import com.capzim.news.webscrapper.NewsdayScrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class GetNewsSchedule {

    private final WebScrapperService webScrapperService;
    private final PublicationService publicationService;


    @Scheduled(cron="0 0 7 ? * * ", zone="CAT") //At 07:00:00am every day
    public void getNews(){
        log.info("Inside getNews of GetNewsSchedule");

        List<Publication> publicationList = publicationService.findAll();

        List<UUID> uuidList = new ArrayList<>();

        for (Publication publication: publicationList){
            uuidList.add(publication.getId());
        }

        webScrapperService.doWebScrapping(uuidList);

    }

}
