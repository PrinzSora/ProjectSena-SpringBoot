package com.project_sena.spring_boot.Membership.Model.Request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterByEmailRequest {
    private String userEmail;
    private String userPassword;
}
