package com.project_sena.spring_boot.Gallery.Entity;

import com.project_sena.spring_boot.Util.Constance.UploadStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table()
public class ImageContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger ID;
    private int UIID;
    private String groupID;
    private String displayName;
    private UploadStatus uploadStatus;
    private byte size;
    private int checkSum;
    private int currentChunk;
    private int totalChunk;
    private LocalDateTime completedUploadDTM;

    private LocalDateTime createdDTM;
    private LocalDateTime updatedDTM;

}
