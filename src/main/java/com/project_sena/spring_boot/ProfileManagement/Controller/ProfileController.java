package com.project_sena.spring_boot.ProfileManagement.Controller;


import com.project_sena.spring_boot.ProfileManagement.Model.Request.CreateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.SearchProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.UpdateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.SearchProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Service.ProfileServices;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value="/profile")
public class ProfileController {

    private final ProfileServices profileServices;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    public ProfileController(ProfileServices profileServices){
        this.profileServices = profileServices;
    }

    @PostMapping("/create_profile")
    public ResponseEntity<ErrorResponses> CreateProfile(@RequestBody CreateProfileRequest request){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            profileServices.CreateProfile(request,timeStamp);
            errorResponses = null;
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }

        return response;
    }

    @PatchMapping("/update_profile")
    public ResponseEntity<ErrorResponses> UpdateProfile(@RequestBody UpdateProfileRequest request){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            profileServices.UpdateProfile(request,timeStamp);
            errorResponses = null;
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            response = new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PostMapping("/search_profile")
    public ResponseEntity<SearchProfileResponses> SearchProfile(@RequestBody SearchProfileRequest request){
        ResponseEntity<SearchProfileResponses> response;
        SearchProfileResponses result = new SearchProfileResponses();
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            profileServices.SearchProfile(request);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(500));
        }
        return  response;
    }

}
