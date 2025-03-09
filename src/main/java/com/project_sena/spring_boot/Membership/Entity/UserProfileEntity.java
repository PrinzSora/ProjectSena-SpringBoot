package com.project_sena.spring_boot.Membership.Entity;

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
@Table(name="user_profile")
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UP_id")
    private BigInteger Id;

    @Column(name="UP_uiid",nullable = false)
    private int UID;

    @Column(name="UP_username",length = 100,nullable = false)
    private String userName;

    @Column(name="UP_password",length = 256,nullable = false)
    private String userPassword;

    @Column(name="UP_status",length = 8,nullable = false)
    private String status;

    @Column(name="UP_created_by",length = 256,nullable = false)
    private String createdBy;

    @Column(name="UP_created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="UP_updated_by",length = 256,nullable = false)
    private String updatedBy;

    @Column(name="UP_updated_DTM",nullable = false)
    private LocalDateTime updatedDTM;

}
