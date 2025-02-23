package com.project_sena.spring_boot.Membership.Model.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String userName;
    private String userPassword;
}
