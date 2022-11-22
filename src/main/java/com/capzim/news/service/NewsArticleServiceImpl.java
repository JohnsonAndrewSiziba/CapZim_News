package com.capzim.news.service;

import com.capzim.news.entity.NewsArticle;
import com.capzim.news.repository.NewsArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
public class NewsArticleServiceImpl implements NewsArticleService{
    private final NewsArticleRepository newsArticleRepository;


    @Override
    public List<NewsArticle> getAllArticles() {
        return newsArticleRepository.findAll();
    }


    @Override
    public List<NewsArticle> getArticlesByDate(Date date) {
        return newsArticleRepository.getNewsArticleByDate(date);
    }


    @Override
    public List<NewsArticle> getTodayArticles() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return newsArticleRepository.getNewsArticleByDate(date);
    }


    @Override
    public NewsArticle saveNewsArticle(NewsArticle newsArticle) {
        return newsArticleRepository.save(newsArticle);
    }


    @Override
    public void deleteNewsArticle(NewsArticle newsArticle) {
        newsArticleRepository.delete(newsArticle);
    }

    @Override
    public NewsArticle getArticleById(UUID id) {
        return newsArticleRepository.findArticleById(id);
    }

    public NewsArticle findNewsArticleByUrl(String url) {
        return newsArticleRepository.findNewsArticleByUrl(url);
    }

    public void deleteOldNewsArticles() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, - 60); // Two months
        java.sql.Date date = new  java.sql.Date(cal.getTimeInMillis());
        List<NewsArticle> newsArticles = newsArticleRepository.findNewsArticlesByDateLessThan(date);
        newsArticleRepository.deleteAll(newsArticles);
    }

    public List<NewsArticle> getAllArticlesPaginated(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        Page<NewsArticle> pagedResult = newsArticleRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<NewsArticle>();
        }
    }

    public long countArticles() {
        return newsArticleRepository.count();
    }
}
