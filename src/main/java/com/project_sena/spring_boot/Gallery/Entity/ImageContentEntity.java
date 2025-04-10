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
@Table(name = "image_content")
public class ImageContentEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="IC_id",columnDefinition = "BIGINT")
    private BigInteger ID;

    @Column(name="IC_uid",nullable = false)
    private int UID;

    @Column(name="IC_groupId")
    private String groupID;

    @Column(name="IC_displayName",nullable = false)
    private String displayName;

    @Column(name="IC_upload-status",nullable = false)
    private UploadStatus uploadStatus;

    @Column(name="IC_size")
    private long size;

    @Column(name="IC_check-sum")
    private int checkSum;

    @Column(name="IC_current-chunk")
    private int currentChunk;

    @Column(name="IC_total-chuck")
    private int totalChunk;

    @Column(name="IC_completed-upload_DTM")
    private LocalDateTime completedUploadDTM;

    @Column(name="IC_created_DTM")
    private LocalDateTime createdDTM;

    @Column(name="IC_update_DTM")
    private LocalDateTime updatedDTM;

}
