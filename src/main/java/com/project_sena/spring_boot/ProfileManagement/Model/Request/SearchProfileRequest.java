package com.project_sena.spring_boot.ProfileManagement.Model.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchProfileRequest {

    private String penName;
    private String gender;
    private String status;

}
