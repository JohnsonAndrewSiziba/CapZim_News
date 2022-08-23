package com.capzim.news.controller;

import com.capzim.news.dto.PublicationRequestDto;
import com.capzim.news.entity.Publication;
import com.capzim.news.service.PublicationServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publications")
@Slf4j
public class PublicationControllerImpl implements PublicationController {
    private final PublicationServiceImpl publicationService;


    /**
     * Get all saved publications
     */
    @Override
    @GetMapping("/")
    @Operation(summary = "Get all publications")
    public List<Publication> getAllPublications() {
        log.info("Getting all publications from database");
        return publicationService.findAll();
    }


    /**
     * Get publication by ID
     */
    @Override
    @GetMapping(value = "/{id}")
    @RouterOperation()
    @Operation(summary = "Get publication by id")
    public Publication getPublicationById(@PathVariable UUID id) {
        log.info("Getting publication: {}", id.toString());
        return publicationService.findPublicationById(id);
    }


    /**
     * get publication by ID and all associated news
     */
    @Override
    @GetMapping("/{id}/news")
    @Operation(summary = "Find publication by ID and get associated news")
    public Publication getPublicationByIdWithNews(@PathVariable UUID id) {
        Publication publication = publicationService.findPublicationById(id);
        publication.setNewsArticles(publication.getNewsArticles());
        return publication;
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
    public Publication editPublication(@RequestBody PublicationRequestDto publicationRequestDto, @PathVariable UUID id) {
        Publication publication = publicationService.findPublicationById(id);
        publication.setName(publicationRequestDto.getName());
        publication.setHomeUrl(publicationRequestDto.getHomeUrl());
        publication.setDescription(publicationRequestDto.getDescription());
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
