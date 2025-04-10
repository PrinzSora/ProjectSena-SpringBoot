package com.project_sena.spring_boot.ProfileManagement.Model.Responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchProfileResponses {
    private List<ProfileResponses> profilesList;
    private ErrorResponses errorResponses;
}
