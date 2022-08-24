package com.capzim.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationRequestModel {
    private String name;
    private String description;
    private String homeUrl;
}
