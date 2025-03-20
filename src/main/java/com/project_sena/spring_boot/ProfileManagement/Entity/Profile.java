package com.project_sena.spring_boot.ProfileManagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name="profile")
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger ID;

    @Column(name="PMP_UID",nullable = false)
    private int UID;

    @Column(name="PMP_penname",nullable = false)
    private String penName;

    @Column(name="PMP_bio")
    private String bio;

    @Column(name="PMP_location")
    private String location;

    @Column(name="PMP_gender")
    private String gender;

    @Column(name="PMP_birth_date")
    private String birthDate;

    @Column(name="PMP_status",nullable = false)
    private String status;

    @Column(name="PMP_created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="PMP_updated_DTM",nullable = false)
    private LocalDateTime updatedDTM;

}
