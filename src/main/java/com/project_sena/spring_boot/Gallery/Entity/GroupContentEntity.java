package com.project_sena.spring_boot.Gallery.Entity;

import com.project_sena.spring_boot.Util.Constance.GroupContentStatus;
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
@Table(name="group_content")
public class GroupContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GC_id",columnDefinition = "BIGINT")
    private BigInteger ID;

    @Column(name="GC_uid",nullable = false)
    private int UID;

    @Column(name="GC_owner",nullable = false)
    private int owner;

    @Column(name="GC_dispaly_name",nullable = false)
    private String displayName;

    @Column(name="GC_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupContentStatus status;

    @Column(name="GC_group-content-rule-id",nullable = false)
    private int groupContentRuleId;

    @Column(name="GC_created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="GC_updated_by",length = 256,nullable = false)
    private String updatedBy;

    @Column(name="GC_updated_DTM",nullable = false)
    private LocalDateTime updatedDTM;

}
