package com.project_sena.spring_boot.ProfileManagement.Model.Responses;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Profile {

    private int UID;

    private String penName;

    private String bio;

    private String location;

    private String gender;

    private String birthDate;

    private String status;

}
