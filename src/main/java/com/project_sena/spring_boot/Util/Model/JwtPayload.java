package com.project_sena.spring_boot.Util.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtPayload {
    private String UID;
    private String userName;
    private String subject;
    private String issuer;
    private String audience;
    private String keyId;
}
