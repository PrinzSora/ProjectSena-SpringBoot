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
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="UP_id")
    private BigInteger Id;

    @Column(name="UP_uiid")
    private BigInteger UID;

    @Column(name="UP_username",length = 100,nullable = false)
    private String userName;

    @Column(name="UP_password",length = 256,nullable = false)
    private String userPassword;

    @Column(length = 256,nullable = false)
    private String userEmail;

    @Column(length = 8,nullable = false)
    private String status;

    @Column(length = 256,nullable = false)
    private LocalDateTime createdBy;

    @Column(nullable = false)
    private LocalDateTime createdDTM;

    @Column(length = 256,nullable = false)
    private LocalDateTime updatedBy;

    @Column(nullable = false)
    private LocalDateTime updatedDTM;

}
