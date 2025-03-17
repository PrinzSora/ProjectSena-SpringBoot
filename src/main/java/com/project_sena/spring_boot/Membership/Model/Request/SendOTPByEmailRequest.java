package com.project_sena.spring_boot.Membership.Model.Request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SendOTPByEmailRequest {
    private String email;
}
