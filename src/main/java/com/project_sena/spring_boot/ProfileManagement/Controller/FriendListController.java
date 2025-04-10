package com.project_sena.spring_boot.ProfileManagement.Controller;

import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/profile-management/friend_list")
public class FriendListController {

    @GetMapping("/get_friend_list")
    public ResponseEntity<ErrorResponses> GetFriendList(){
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

    @GetMapping("/friend_request")
    public ResponseEntity<ErrorResponses> FriendRequest(){
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

    @PatchMapping("/update_friend_list")
    public ResponseEntity<ErrorResponses> UpdateFriendRequest(){
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

}
