package com.project_sena.spring_boot.Gallery.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "content")
public class ContentEntity {
    @Id
    private BigInteger id;
    private BigInteger image_id;
    private String image_url;
    private String caption;
    @ElementCollection
    private List<String> genre;
    @ElementCollection
    private List<String> tag;
    @ElementCollection
    private List<String> like;
    @ElementCollection
    private List<CommentForContentEntity> comment;
    private String status;
    private LocalDateTime createdDTM;
    private LocalDateTime updatedDTM;
}
