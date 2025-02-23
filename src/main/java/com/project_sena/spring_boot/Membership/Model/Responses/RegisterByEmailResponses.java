package com.project_sena.spring_boot.Membership.Model.Responses;

import com.project_sena.spring_boot.UtilService.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegisterByEmailResponses extends ErrorResponses {
    private LocalDateTime OTPExpr;
    private String OTPRef;
}
