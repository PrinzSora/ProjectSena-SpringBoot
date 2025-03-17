package com.project_sena.spring_boot.Util.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponses {
    private String code;
    private String messages;
    private String detail;
}
