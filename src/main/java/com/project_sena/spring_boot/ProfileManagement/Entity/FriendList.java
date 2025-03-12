package com.project_sena.spring_boot.ProfileManagement.Entity;

import com.project_sena.spring_boot.ProfileManagement.Constance.FriendStatus;
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
    @GeneratedValue()
    private BigInteger id;

    private int userUID;

    private int friendUID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendStatus status;

    private LocalDateTime createdDTM;

    private LocalDateTime updatedDTM;

}
