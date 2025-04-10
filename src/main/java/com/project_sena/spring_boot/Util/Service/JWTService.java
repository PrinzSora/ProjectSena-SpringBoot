package com.project_sena.spring_boot.Util.Service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project_sena.spring_boot.Util.Model.JwtPayload;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;

@Service
public class JWTService {

    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static String secretKey = "thisissecretkeyforprojectsena";

    public JWTService(UtilService utilService) {
//        if(Objects.equals(secretKey,null)){
//            synchronized(JWTService.class){
//                if(Objects.equals(secretKey,null)){
//                    secretKey = utilService.GenerateSecretKeyRandom();
//                }
//            }
//        }
    }

    public String encodeJWTToken(JwtPayload jwtPayload){
        String jwt = JWT.create()
                //.withIssuer("")
                //.withAudience("")
                //.withKeyId("")
                .withSubject(jwtPayload.getUID())
                .withExpiresAt(Date.valueOf(LocalDateTime.now(ZoneOffset.UTC).plusDays(1).toLocalDate()))
                .sign(Algorithm.HMAC256(secretKey));
        return jwt;
    }

    public String decodeJWTToken(String JWTToken) throws Exception{
            String result = new String();
            if(JWTToken.startsWith("Bearer ")){
                JWTToken = JWTToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                result = verifier.verify(JWTToken).getSubject();
            }
            return result;
    }

}
