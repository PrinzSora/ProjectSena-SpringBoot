package com.project_sena.spring_boot.ProfileManagement.Entity;

import com.project_sena.spring_boot.Util.Constance.FriendStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name="friend_list")
@NoArgsConstructor
@Getter
@Setter
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger ID;

    @Column(name="PHF_user_uid",nullable = false)
    private int userUID;

    @Column(name="PHF_friend_uid",nullable = false)
    private int friendUID;

    @Enumerated(EnumType.STRING)
    @Column(name="PHF_status" ,nullable = false)
    private FriendStatus status;

    @Column(name="PHF_created_DTM" ,nullable = false)
    private LocalDateTime createdDTM;

    @Column(name="PHF_updated_DTM" ,nullable = false)
    private LocalDateTime updatedDTM;

}
