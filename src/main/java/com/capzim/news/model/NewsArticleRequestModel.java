package com.capzim.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsArticleRequestModel {
    private String title;
    private Date date;
    private String url;
    private String imageUrl;
    private String articleContent;
    private UUID publicationId;
}
