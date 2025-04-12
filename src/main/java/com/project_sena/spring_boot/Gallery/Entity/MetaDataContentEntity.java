package com.project_sena.spring_boot.Gallery.Entity;

import com.project_sena.spring_boot.Util.Constance.UploadStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="meta-data-content")
public class MetaDataContentEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="id",columnDefinition = "BIGINT")
    private BigInteger ID;

    @Column(name="uid",nullable = false)
    private int UID;

    @Column(name="groupId")
    private String groupID;

    @Column(name="displayName",nullable = false)
    private String displayName;

    @Column(name="upload-status",nullable = false)
    private UploadStatus uploadStatus;

    @Column(name="size")
    private long size;

    @Column(name="check-sum")
    private int checkSum;

    @Column(name="current-chunk")
    private int currentChunk;

    @Column(name="total-chuck")
    private int totalChunk;

    @Column(name="completed-upload_DTM")
    private LocalDateTime completedUploadDTM;

    @Column(name="created_DTM")
    private LocalDateTime createdDTM;

    @Column(name="update_DTM")
    private LocalDateTime updatedDTM;
}
