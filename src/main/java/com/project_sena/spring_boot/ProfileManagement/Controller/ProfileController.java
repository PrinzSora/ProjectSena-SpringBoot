package com.project_sena.spring_boot.ProfileManagement.Controller;


import com.project_sena.spring_boot.ProfileManagement.Model.Responses.SearchProfileResponses;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ProfileManagement")
public class ProfileController {

    @PostMapping("/create_profile")
    public ResponseEntity<ErrorResponses> CreateProfile(){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{

            errorResponses = null;
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            response = new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }

        return response;
    }

    @PatchMapping("/update_profile")
    public ResponseEntity<ErrorResponses> UpdateProfile(){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{

            errorResponses = null;
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            response = new ResponseEntity<>(null, HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @GetMapping("/search_profile")
    public ResponseEntity<SearchProfileResponses> SearchProfile(){
        ResponseEntity<SearchProfileResponses> response;
        SearchProfileResponses result = new SearchProfileResponses();
        ErrorResponses errorResponses = new ErrorResponses();
        try{

            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(500));
        }
        return  response;
    }

}
