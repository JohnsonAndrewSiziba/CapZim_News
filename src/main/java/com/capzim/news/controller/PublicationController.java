package com.capzim.news.controller;

import com.capzim.news.model.PublicationNameResponseModel;
import com.capzim.news.model.PublicationRequestModel;
import com.capzim.news.entity.Publication;

import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

public interface PublicationController {
    public List<PublicationNameResponseModel> getAllPublications();

    public PublicationNameResponseModel getPublicationById(UUID id);

    public Publication getPublicationByIdWithNews(UUID id);

    public Publication getPublicationByName(String name);

    public Publication getPublicationByNameWithNews(String name);

    public Publication savePublication(Publication publication);

    public Publication editPublication(PublicationRequestModel publication, UUID id);

    public void removePublication(UUID id);

}
