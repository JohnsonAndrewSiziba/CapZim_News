package com.capzim.news.controller;

import com.capzim.news.model.PublicationNameResponseModel;
import com.capzim.news.model.PublicationRequestModel;
import com.capzim.news.entity.Publication;
import com.capzim.news.service.PublicationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news/publications")
@Slf4j
@EnableDiscoveryClient
public class PublicationControllerImpl implements PublicationController {
    private final PublicationServiceImpl publicationService;


    /**
     * Get all saved publications
     */
    @Override
    @GetMapping("/")
    @Operation(summary = "Get all publications")
    public List<PublicationNameResponseModel> getAllPublications() {
        log.info("Getting all publications from database");
        List<Publication> publications = publicationService.findAll();

        List<PublicationNameResponseModel> publicationNameResponseModels = new ArrayList<>();

        for (Publication publication : publications)
        {
            PublicationNameResponseModel publicationNameResponseModel = new PublicationNameResponseModel(publication);
            publicationNameResponseModels.add(publicationNameResponseModel);
        }

        return publicationNameResponseModels;
    }


    /**
     * Get publication by ID
     */
    @Override
    @GetMapping(value = "/{id}")
    @RouterOperation()
    @Operation(summary = "Get publication by id")
    public PublicationNameResponseModel getPublicationById(@PathVariable UUID id) {
        log.info("Getting publication: {}", id.toString());
        Publication publication = publicationService.findPublicationById(id);
        return new PublicationNameResponseModel(publication);
    }


    /**
     * get publication by ID and all associated news
     */
    @Override
    @GetMapping("/{id}/news_articles")
    @Operation(summary = "Find publication by ID and get associated news")
    public Publication getPublicationByIdWithNews(@PathVariable UUID id) {
        return publicationService.findPublicationById(id);
    }


    /**
     * Get publication by name
     */
    @Override
    @GetMapping("/name/{name}")
    @Operation(summary = "Get publication by name")
    public Publication getPublicationByName(@PathVariable String name) {
        return publicationService.findPublicationByName(name);
    }


    /**
     * Get publication by name and all associated news
     */
    @Override
    @PostMapping("/name/{name}/news")
    @Operation(summary = "Find publication by name and get associated news")
    public Publication getPublicationByNameWithNews(@PathVariable String name) {
        Publication publication = publicationService.findPublicationByName(name);
        publication.setNewsArticles(publication.getNewsArticles());
        return publication;
    }


    /**
     * Add new publication to database
     */
    @Override
    @PostMapping("/")
    @Operation(summary = "Add new publication")
    public Publication savePublication(@RequestBody Publication publication) {
        return publicationService.savePublication(publication);
    }


    /**
     * Update saved publication
     */
    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update publication")
    public Publication editPublication(@RequestBody PublicationRequestModel publicationRequestModel, @PathVariable UUID id) {
        Publication publication = publicationService.findPublicationById(id);
        publication.setName(publicationRequestModel.getName());
        publication.setHomeUrl(publicationRequestModel.getHomeUrl());
        publication.setDescription(publicationRequestModel.getDescription());
        publicationService.savePublication(publication);
        return publicationService.savePublication(publication);
    }


    /**
     * Delete saved publication
     */
    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete publications")
    public void removePublication(@PathVariable UUID id) {
        Publication publication = publicationService.findPublicationById(id);
        publicationService.remove(publication);
    }
}
