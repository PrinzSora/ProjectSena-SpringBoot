package com.project_sena.spring_boot.Membership.Model.Responses;

import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponses {
    private String userToken;
    private ErrorResponses errorResponses;
}
