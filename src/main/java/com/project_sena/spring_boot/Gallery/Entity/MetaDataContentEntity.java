package com.project_sena.spring_boot.Gallery.Entity;

import com.project_sena.spring_boot.Util.Constance.MediaType;
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
@Table(name="meta_data_content")
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

    @Enumerated(EnumType.STRING)
    @Column(name="upload_status",nullable = false)
    private UploadStatus uploadStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="media_type",nullable = false)
    private MediaType mediaType;

    @Column(name="size",nullable = false)
    private long size;

    @Column(name="check_sum",nullable = false)
    private int checkSum;

    @Column(name="current_chunk",nullable = false)
    private int currentChunk;

    @Column(name="total_chuck",nullable = false)
    private int totalChunk;

    @Column(name="completed_upload_DTM")
    private LocalDateTime completedUploadDTM;

    @Column(name="created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="update_DTM")
    private LocalDateTime updatedDTM;
}
