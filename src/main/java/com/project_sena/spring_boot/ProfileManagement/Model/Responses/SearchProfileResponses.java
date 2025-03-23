package com.project_sena.spring_boot.ProfileManagement.Model.Responses;


import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SearchProfileResponses {
    private List<Profile> profilesList;
    private ErrorResponses errorResponses;
}
