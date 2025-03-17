package com.project_sena.spring_boot.Membership.Model.Responses;

import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegisterByEmailResponses {
    private LocalDateTime OTPExpr;
    private String OTPRef;
    private ErrorResponses errorResponses;
}
