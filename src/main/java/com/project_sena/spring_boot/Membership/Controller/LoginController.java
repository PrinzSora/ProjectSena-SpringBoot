package com.project_sena.spring_boot.Membership.Controller;


import com.project_sena.spring_boot.Membership.Model.Request.LoginRequest;
import com.project_sena.spring_boot.Membership.Model.Responses.LoginResponses;
import com.project_sena.spring_boot.Membership.Repository.UserProfileRepo;
import com.project_sena.spring_boot.Membership.Service.LoginService;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "membership/login")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login_with_email")
    public ResponseEntity<LoginResponses> LoginWithEmail(@RequestBody LoginRequest request){
        ResponseEntity<LoginResponses> response;
        LoginResponses result = new LoginResponses();
        try{
            result = loginService.LoginWithEmail(request);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            ErrorResponses errorResponses = new ErrorResponses();
            errorResponses.setCode("5001");
            errorResponses.setDetail(e.getMessage());
            errorResponses.setMessages(e.getClass().getSimpleName());
            response = new ResponseEntity<>(null,HttpStatusCode.valueOf(500));
        }
        return response;
    }



}
