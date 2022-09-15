package com.capzim.news.schedule;

import com.capzim.news.entity.Publication;
import com.capzim.news.service.NewsArticleServiceImpl;
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
 * Date: 15/9/2022
 * Time: 10:28
 */


@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteOldNewsSchedule {
    private final NewsArticleServiceImpl newsArticleService;

    @Scheduled(cron="0 0 4 ? * * ", zone="CAT") //At 04:00:00am every day
    public void deleteOldNews(){
        log.info("Inside deleteOldNews of DeleteOldNewsSchedule");
        newsArticleService.deleteOldNewsArticles();
        log.info("Old articles deleted");
    }
}
