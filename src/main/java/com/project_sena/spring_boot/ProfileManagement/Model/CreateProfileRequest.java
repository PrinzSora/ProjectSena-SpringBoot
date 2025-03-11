package com.project_sena.spring_boot.ProfileManagement.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CreateProfileRequest {

    private String penName;

    private String bio;

    private String location;

    private String gender;

    private LocalDateTime birthDate;

    private String status;

    private LocalDateTime createdDTM;

    private LocalDateTime updatedDTM;

}
