package com.project_sena.spring_boot.Membership.Model.Responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponses {
    private String userToken;
    private ErrorResponses errorResponses;
}
