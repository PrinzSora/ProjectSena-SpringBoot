package com.project_sena.spring_boot.ProfileManagement.Model.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UpdateProfileRequest {

    private String bio;

    private String location;

    private String gender;

    private String status;

}
