package com.project_sena.spring_boot.Gallery.Model.Request;

import com.project_sena.spring_boot.Util.Constance.UploadStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ContentUploadRequest {

    private String displayName;
    private String fileFormat;
    private byte size;
    private int checkSum;
    private int currentChunk;
    private int totalChunk;
    private LocalDateTime completedUploadDTM;

    private LocalDateTime createdDTM;
    private LocalDateTime updatedDTM;

}
