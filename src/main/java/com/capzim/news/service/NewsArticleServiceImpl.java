package com.capzim.news.service;

import com.capzim.news.repository.NewsArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
public class NewsArticleServiceImpl implements NewsArticleService{
    private final NewsArticleRepository newsArticleRepository;

}
