package com.capzim.news.model;

import com.capzim.news.entity.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 * Publication details without articles list
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationNameResponseModel {
    private UUID id;
    private String name;
    private String description;
    private String homeUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PublicationNameResponseModel(Publication publication){
        this.setId(publication.getId());
        this.setName(publication.getName());
        this.setDescription(publication.getDescription());
        this.setHomeUrl(publication.getHomeUrl());
        this.setCreatedAt(publication.getCreatedAt());
        this.setUpdatedAt(publication.getUpdatedAt());
    }
}
