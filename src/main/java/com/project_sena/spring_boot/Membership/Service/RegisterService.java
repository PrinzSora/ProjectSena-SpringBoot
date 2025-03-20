package com.project_sena.spring_boot.Membership.Service;

import com.project_sena.spring_boot.Util.CostumException.DataAlreadyExistsException;
import com.project_sena.spring_boot.Util.CostumException.TimeExpiredException;
import com.project_sena.spring_boot.Membership.Entity.TempRegisterEntity;
import com.project_sena.spring_boot.Membership.Entity.UserProfileEntity;
import com.project_sena.spring_boot.Membership.Model.Request.RegisterByEmailRequest;
import com.project_sena.spring_boot.Membership.Model.Responses.RegisterByEmailResponses;
import com.project_sena.spring_boot.Membership.Repository.TempRegisterRepo;
import com.project_sena.spring_boot.Membership.Repository.UserProfileRepo;
import com.project_sena.spring_boot.Util.Service.MailService;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class RegisterService {

    private final TempRegisterRepo tempRegisterRepo;
    private final UserProfileRepo userProfileRepo;
    private final UtilService utilService;
    private final MailService mailService;
    private String OTPRef;
    private String OTP;
    private LocalDateTime OTPExpr;

    public RegisterService(TempRegisterRepo tempRegisterRepo, UserProfileRepo userProfileRepo, UtilService utilService, MailService mailService) {
        this.tempRegisterRepo = tempRegisterRepo;
        this.userProfileRepo = userProfileRepo;
        this.utilService = utilService;
        this.mailService = mailService;
    }

    @Transactional
    public RegisterByEmailResponses RegisterWithEmail(RegisterByEmailRequest request,LocalDateTime timeStamp) throws Exception {
        RegisterByEmailResponses result = new RegisterByEmailResponses();
        OTPRef = utilService.GenerateOTPRef();
        OTP = utilService.GenerateOTP();
        OTPExpr = timeStamp.plusHours(1);
        if(isUserExistedInUserProfile(request.getUserEmail())){
            throw new DataAlreadyExistsException("This Email existed");
        }
        if(isUserInRegisterProcess(request.getUserEmail())){
            throw new DataAlreadyExistsException("This Email is already in registration process");
        }
        isRequestFormatValid(request);
        tempRegisterRepo.save(createNewTempUser(request,timeStamp));
        mailService.sendSimpleEmail(request.getUserEmail(),"Testing mail for OTP",this.generateEmailBodyForSendingOTP());
        result.setOTPRef(OTPRef);
        result.setOTPExpr(OTPExpr);
        return result;
    }

    @Transactional
    public void verifyWithOTP(String otp,String userName,LocalDateTime timeStamp) throws Exception{
        Optional<TempRegisterEntity> tempRegister = tempRegisterRepo.findByuserName(userName);
        if(tempRegister.isEmpty()){
            throw new AccountNotFoundException("User not found");
        }
        if(Objects.equals(tempRegister.get().getVerificationState(),"ACTIVE")){
            throw new DataAlreadyExistsException("This account is ACTIVE");
        }
        if(tempRegister.get().getOTPExprDTM().isBefore(timeStamp)){
            throw new TimeExpiredException("OTP Expired");
        }
        if(Objects.equals(tempRegister.get().getOTPCode(), otp)){
            tempRegister.get().setVerificationState("ACTIVE");
            tempRegister.get().setUpdatedDTM(timeStamp);
            tempRegister.get().setUpdatedBy("System");
            tempRegisterRepo.save(tempRegister.get());
            userProfileRepo.save(this.createNewUserProfile(tempRegister.get(),timeStamp));
        }
        else{
            throw new IllegalArgumentException("OTP miss matched");
        }
    }

    private TempRegisterEntity createNewTempUser(RegisterByEmailRequest request,LocalDateTime timeStamp) throws Exception {
        TempRegisterEntity result = new TempRegisterEntity();
        result.setUserName(request.getUserEmail());
        result.setUserPassword(utilService.HashStringWithSha256(request.getUserPassword()));
        result.setOTPCode(OTP);
        result.setOTPRef(OTPRef);
        result.setOTPSendDTM(timeStamp);
        result.setOTPExprDTM(timeStamp.plusHours(1));
        result.setStatus("PENDING");
        result.setVerificationState("PENDING");
        result.setCreatedBy("System");
        result.setCreatedDTM(timeStamp);
        result.setUpdatedBy("System");
        result.setUpdatedDTM(timeStamp);
        return result;
    }

    private UserProfileEntity createNewUserProfile(TempRegisterEntity tempRegister,LocalDateTime timeStamp) throws Exception{
        UserProfileEntity result = new UserProfileEntity();
        result.setUID(12345);
        result.setUserName(tempRegister.getUserName());
        result.setUserPassword(tempRegister.getUserPassword());
        result.setStatus("ACTIVE");
        result.setCreatedBy("System");
        result.setCreatedDTM(timeStamp);
        result.setUpdatedBy("System");
        result.setCreatedDTM(timeStamp);
        result.setUpdatedDTM(timeStamp);
        return result;
    }

    private void isRequestFormatValid(RegisterByEmailRequest request) throws Exception{
        if(!utilService.IsEmailFormatCorrect(request.getUserEmail())){
            throw new IllegalArgumentException("Incorrect of email format");
        }
        if(!utilService.IsPasswordFormatCorrect(request.getUserPassword())){
            throw new IllegalArgumentException("Incorrect of password format");
        }
    }

    private boolean isUserExistedInUserProfile(String userName){
        return userProfileRepo.existsByuserName(userName);
    }

    private boolean isUserInRegisterProcess(String userName) {
        boolean result = false;
        //todo
        //Optional<TempRegisterEntity> tempRegister = tempRegisterRepo.findByuserName(userName);
        if(tempRegisterRepo.existsByuserName(userName)){
            result = true;
        }
        return result;
    }

    private String generateEmailBodyForSendingOTP(){
        return "OTP:"+this.OTP + "OTP Ref:"+this.OTPRef + "OTP Expr:"+OTPExpr;
    }

}
