package com.project_sena.spring_boot.Membership.Service;

import com.project_sena.spring_boot.Membership.CostumException.DataAlreadyExistsException;
import com.project_sena.spring_boot.Membership.Model.Request.RegisterByEmailRequest;
import com.project_sena.spring_boot.Membership.Model.Responses.RegisterByEmailResponses;
import com.project_sena.spring_boot.Membership.Repository.TempRegisterRepo;
import com.project_sena.spring_boot.Membership.Repository.UserProfileRepo;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final TempRegisterRepo tempRegisterRepo;
    private final UserProfileRepo userProfileRepo;
    public RegisterService(TempRegisterRepo tempRegisterRepo,UserProfileRepo userProfileRepo) {
        this.tempRegisterRepo = tempRegisterRepo;
        this.userProfileRepo = userProfileRepo;
    }

    public RegisterByEmailResponses RegisterWithEmail(RegisterByEmailRequest request) throws Exception {
        RegisterByEmailResponses result = new RegisterByEmailResponses();

        if(isUserExisted(request.getUserEmail())){
            throw new DataAlreadyExistsException();
        }
        if(isUserInRegisterProcess(request.getUserEmail())){
            throw new DataAlreadyExistsException();
        }
        isRequestFormatValid();


        return result;
    }



    private void isRequestFormatValid(){
    }

    private boolean isUserExisted(String userName){
        return userProfileRepo.existsByuserName(userName);
    }

    private boolean isUserInRegisterProcess(String userName){
        return tempRegisterRepo.existsByuserName(userName);
    }


}
