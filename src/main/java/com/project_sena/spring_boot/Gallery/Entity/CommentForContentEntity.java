package com.project_sena.spring_boot.Gallery.Entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommentForContentEntity {

    @Id
    private BigInteger id;
    private int comment_uid;
    private int like;
    private String status;
    private LocalDateTime createdDTM;
    private LocalDateTime updatedDTM;

}
