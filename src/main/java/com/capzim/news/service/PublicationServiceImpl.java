package com.capzim.news.service;

import com.capzim.news.entity.NewsArticle;
import com.capzim.news.entity.Publication;
import com.capzim.news.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements PublicationService{

    private final PublicationRepository publicationRepository;

    @Override
    public Publication savePublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication findPublicationById(UUID id) {
        return publicationRepository.findPublicationById(id);
    }

    @Override
    public Publication findPublicationByName(String name) {
        return publicationRepository.findPublicationByName(name);
    }


    @Override
    public NewsArticle addNewsArticleToPublication(NewsArticle newsArticle) {
        return null;
    }

    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public void remove(Publication publication) {
        publicationRepository.delete(publication);
    }
}
