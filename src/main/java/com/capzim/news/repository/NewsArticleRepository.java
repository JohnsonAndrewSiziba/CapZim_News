package com.capzim.news.repository;

import com.capzim.news.entity.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

public interface NewsArticleRepository extends JpaRepository<NewsArticle, UUID> {
}
