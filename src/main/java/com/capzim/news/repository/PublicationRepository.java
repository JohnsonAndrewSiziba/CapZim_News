package com.capzim.news.repository;

import com.capzim.news.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

public interface PublicationRepository extends JpaRepository<Publication, UUID> {
    Publication findPublicationById(UUID id);

    Publication findPublicationByName(String name);
}
