package com.capzim.news.service;

import com.capzim.news.entity.NewsArticle;
import com.capzim.news.entity.Publication;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

public interface PublicationService {

    public Publication savePublication(Publication publication);

    public Publication findPublicationById(UUID id);

    public Publication findPublicationByName(String name);

    public NewsArticle addNewsArticleToPublication(NewsArticle newsArticle);

    List<Publication> findAll();

    public void remove(Publication publication);
}
