package com.project_sena.spring_boot.Membership.Service;


import com.project_sena.spring_boot.Membership.Entity.UserProfileEntity;
import com.project_sena.spring_boot.Membership.Model.Request.LoginRequest;
import com.project_sena.spring_boot.Membership.Model.Responses.LoginResponses;
import com.project_sena.spring_boot.Membership.Repository.UserProfileRepo;
import com.project_sena.spring_boot.Util.Service.JWTService;
import com.project_sena.spring_boot.Util.Model.JwtPayload;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LoginService {

    private final UserProfileRepo userProfileRepo;
    private final JWTService jwtService;
    private final UtilService utilService;

    public LoginService(UserProfileRepo userProfileRepo, JWTService jwtService, UtilService utilService) {
        this.userProfileRepo = userProfileRepo;
        this.jwtService = jwtService;
        this.utilService = utilService;
    }

    //todo Brute force protection (set login attempt)
    public LoginResponses LoginWithEmail(LoginRequest loginRequest) throws Exception{
        LoginResponses result = new LoginResponses();
        Optional<UserProfileEntity> userProfileEntity = userProfileRepo.findByuserName(loginRequest.getUserName());
        if(userProfileEntity.isEmpty()){
            throw new IllegalArgumentException("Account not found");
        }
        if(Objects.equals(userProfileEntity.get().getStatus(),"DEACTIVATE")){
            throw new Exception("Your Account Deactivate");
        }
        if(Objects.equals(userProfileEntity.get().getUserPassword(),utilService.HashStringWithSha256(loginRequest.getUserPassword()))){
            JwtPayload jwtPayload = new JwtPayload();
            jwtPayload.setUID(String.valueOf(userProfileEntity.get().getUID()));
            jwtPayload.setUserName(userProfileEntity.get().getUserName());
            result.setUserToken(jwtService.encodeJWTToken(jwtPayload));
        }else{
            throw new IllegalArgumentException("Wrong password");
        }
        return result;
    }
}
