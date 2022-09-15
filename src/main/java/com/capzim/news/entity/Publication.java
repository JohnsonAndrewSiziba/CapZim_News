package com.capzim.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Johnson Andrew Siziba (sizibajohnsona@gmail.com,+263784310119)
 * @version 1.0
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Publication {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    public UUID id;


    private String name;
    private String description;
    private String homeUrl;

    @Lob
    private byte[] publicationLogoFile;
    private String publicationLogoFileName;
    private String publicationLogoFileType;


    @OneToMany(
            mappedBy = "publication",
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    @JsonIgnoreProperties("publication")
    private List<NewsArticle> newsArticles;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Publication that = (Publication) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
