package com.project_sena.spring_boot.Membership.Controller;

import com.project_sena.spring_boot.Membership.Model.Request.RegisterByEmailRequest;
import com.project_sena.spring_boot.Membership.Model.Responses.RegisterByEmailResponses;
import com.project_sena.spring_boot.Membership.Model.Responses.SendOTPByEmailResponses;

import com.project_sena.spring_boot.Membership.Service.RegisterService;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@RequestMapping(value = "membership/register")
public class RegisterController {
    private static final Logger logger = LogManager.getLogger(RegisterController.class);
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final RegisterService registerService;
    private final HttpServletRequest httpServletRequest;

    public RegisterController(RegisterService registerService, HttpServletRequest httpServletRequest) {
        this.registerService = registerService;
        this.httpServletRequest = httpServletRequest;
    }

    @PostMapping("/register_with_email")
    public ResponseEntity<RegisterByEmailResponses> RegisterWithEmail(@RequestBody RegisterByEmailRequest request){
        ResponseEntity<RegisterByEmailResponses> response;
        RegisterByEmailResponses result = new RegisterByEmailResponses();
        try{
            result = registerService.RegisterWithEmail(request,timeStamp);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            ErrorResponses errorResponses = new ErrorResponses();
            errorResponses.setCode("5001");
            errorResponses.setDetail(e.getMessage());
            errorResponses.setMessages(e.getClass().getSimpleName());
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result,HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @GetMapping("/send_otp_email/{email}")
    public ResponseEntity<SendOTPByEmailResponses> SendOTPByEmail(){
        ResponseEntity<SendOTPByEmailResponses> response;
        SendOTPByEmailResponses result = new SendOTPByEmailResponses();
        try{
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            ErrorResponses errorResponses = new ErrorResponses();
            errorResponses.setCode("5001");
            errorResponses.setDetail(e.getClass().descriptorString());
            errorResponses.setMessages(e.getClass().getSimpleName());
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result,HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @GetMapping("/verify_otp/{otp}/{email}")
    public ResponseEntity<ErrorResponses> VerifyOTP(@PathVariable("otp") String otp,@PathVariable("email") String email){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            registerService.verifyWithOTP(otp,email,timeStamp);
            response = new ResponseEntity<>(errorResponses,HttpStatusCode.valueOf(200));
        }catch (Exception e){
            errorResponses.setCode("5001");
            errorResponses.setDetail(e.getMessage());
            errorResponses.setMessages(e.getClass().getSimpleName());
            response = new ResponseEntity<>(errorResponses,HttpStatusCode.valueOf(500));
        }
        return response;
    }

}

