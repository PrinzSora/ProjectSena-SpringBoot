package com.project_sena.spring_boot.ProfileManagement.Entity;

import com.project_sena.spring_boot.Util.Constance.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="profile")
@NoArgsConstructor
@Getter
@Setter
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="PMP_id",columnDefinition = "BIGINT")
    private BigInteger ID;

    @Column(name="PMP_uid",unique = true,nullable = false)
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
    private LocalDateTime birthDate;

    @Column(name="PMP_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @OneToMany(mappedBy = "userUID",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FriendListEntity> friendListEntity;

    @Column(name="PMP_created_DTM",nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="PMP_updated_DTM",nullable = false)
    private LocalDateTime updatedDTM;

}
