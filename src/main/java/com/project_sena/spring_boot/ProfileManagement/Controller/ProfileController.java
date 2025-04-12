package com.project_sena.spring_boot.ProfileManagement.Controller;


import com.project_sena.spring_boot.ProfileManagement.Model.Request.CreateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.SearchProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.UpdateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.ProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.SearchProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Service.ProfileServices;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import com.project_sena.spring_boot.Util.Service.ThreadLocalService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value="/profile-management/profile")
public class ProfileController {

    private final ProfileServices profileServices;
    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final ThreadLocalService threadLocalService;

    public ProfileController(ProfileServices profileServices, ThreadLocalService threadLocalService){
        this.profileServices = profileServices;
        this.threadLocalService = threadLocalService;
    }

    @GetMapping("/get_profile")
    public ResponseEntity<ProfileResponses> GetProfile(){
        ResponseEntity<ProfileResponses> response;
        ProfileResponses result = new ProfileResponses();
        ErrorResponses errorResponses = new ErrorResponses();

        try{
            result = profileServices.GetProfile();
            errorResponses = null;
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result , HttpStatusCode.valueOf(200));
        }catch (Exception e){
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getMessage());
            errorResponses.setDetail(e.getClass().getSimpleName());
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(500));
        }
        return response;
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
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getMessage());
            errorResponses.setDetail(e.getClass().getSimpleName());
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PatchMapping("/update_profile")
    public ResponseEntity<ErrorResponses> UpdateProfile(@RequestBody UpdateProfileRequest request){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            profileServices.UpdateProfile(request,timeStamp);
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getLocalizedMessage());
            errorResponses.setDetail(e.getMessage());
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @DeleteMapping("/delete_profile")
    public ResponseEntity<ErrorResponses> DeleteProfile(){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            profileServices.DeleteProfile();
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getLocalizedMessage());
            errorResponses.setDetail(e.getMessage());
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(500));
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
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getLocalizedMessage());
            errorResponses.setDetail(e.getMessage());
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(500));
        }
        return  response;
    }

}
