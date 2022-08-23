package com.capzim.news.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsArticle {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    public UUID id;

    private String title;
    private Date date;
    private String url;
    private String imageUrl;
    private String articleContent;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    private Publication publication;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
