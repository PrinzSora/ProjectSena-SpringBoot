package com.project_sena.spring_boot.Util;


import com.auth0.jwt.HeaderParams;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project_sena.spring_boot.Util.Model.JwtPayload;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class JWTService {

    private static String secretKey = null;

    public JWTService(UtilService utilService) {

        if(Objects.equals(secretKey,null)){
            synchronized(JWTService.class){
                if(Objects.equals(secretKey,null)){
                    secretKey = utilService.GenerateSecretKeyRandom();
                }
            }
        }
    }

    public String encodeJWTToken(JwtPayload jwtPayload){
        String jwt = JWT.create()
                .withIssuer("")
                .withSubject(jwtPayload.getUserName())
                .withAudience("")
                .withKeyId("")
                .withExpiresAt(Date.valueOf(LocalDateTime.now().plusHours(1).toLocalDate()))
                .withIssuedAt(Date.valueOf(LocalDateTime.now().toLocalDate()))
                .withClaim("UID",jwtPayload.getUID())
                .sign(Algorithm.HMAC256(secretKey));
        return jwt;
    }

    public DecodedJWT decodeJWTToken(String JWTToken){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(JWTToken);
        return jwt;
    }


}
