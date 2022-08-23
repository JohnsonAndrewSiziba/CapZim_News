package com.capzim.news.controller;

import com.capzim.news.dto.PublicationRequestDto;
import com.capzim.news.entity.Publication;

import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

public interface PublicationController {
    public List<Publication> getAllPublications();

    public Publication getPublicationById(UUID id);

    public Publication getPublicationByIdWithNews(UUID id);

    public Publication getPublicationByName(String name);

    public Publication getPublicationByNameWithNews(String name);

    public Publication savePublication(Publication publication);

    public Publication editPublication(PublicationRequestDto publication, UUID id);

    public void removePublication(UUID id);

}
