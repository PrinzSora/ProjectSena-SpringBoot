package com.project_sena.spring_boot.ProfileManagement.Model.Responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponses {

    private int UID;

    private String penName;

    private String bio;

    private String location;

    private String gender;

    private String birthDate;

    private ErrorResponses errorResponses;

}
