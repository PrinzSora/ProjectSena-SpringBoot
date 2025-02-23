package com.project_sena.spring_boot.Membership.Controller;

import com.project_sena.spring_boot.Membership.Model.Request.RegisterByEmailRequest;
import com.project_sena.spring_boot.Membership.Model.Responses.RegisterByEmailResponses;
import com.project_sena.spring_boot.Membership.Repository.TempRegisterRepo;

import com.project_sena.spring_boot.Membership.Service.RegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "membership/register")
public class RegisterController {
    private static final Logger logger = LogManager.getLogger(RegisterController.class);
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping("/register_with_email")
    public ResponseEntity<RegisterByEmailResponses> RegisterWithEmail(@RequestBody RegisterByEmailRequest request){
        ResponseEntity<RegisterByEmailResponses> response;
        RegisterByEmailResponses result = new RegisterByEmailResponses();
        try{
            result = registerService.RegisterWithEmail(request);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            response = new ResponseEntity<>(null,HttpStatusCode.valueOf(500));
        }
        return response;

    }
}
