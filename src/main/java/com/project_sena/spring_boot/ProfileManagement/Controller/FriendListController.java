package com.project_sena.spring_boot.ProfileManagement.Controller;

import com.project_sena.spring_boot.ProfileManagement.Model.Request.SendFriendInviteRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.FriendListResponses;
import com.project_sena.spring_boot.ProfileManagement.Service.FriendListService;
import com.project_sena.spring_boot.Util.Constance.FriendStatus;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping(value="/profile-management/friend-list")
public class FriendListController {

    private final FriendListService friendListService;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    public FriendListController(FriendListService friendListService) {
        this.friendListService = friendListService;
    }


    @GetMapping("/get_friend_list")
    public ResponseEntity<FriendListResponses> GetFriendList(){
        ResponseEntity<FriendListResponses> response;
        FriendListResponses result = new FriendListResponses();
        try{
            result = friendListService.getFriendList();
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            ErrorResponses errorResponses = new ErrorResponses();
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getLocalizedMessage());
            errorResponses.setDetail(e.getMessage());
            result.setErrorResponses(errorResponses);
            response = new ResponseEntity<>(result, HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PostMapping("/send_friend_invite")
    public ResponseEntity<ErrorResponses> SendFriendInvite(@RequestBody SendFriendInviteRequest bodyRequest){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            friendListService.sendFriendInvite(bodyRequest.getFriendUID(),timeStamp);
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getLocalizedMessage());
            errorResponses.setDetail(e.getMessage());
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PatchMapping("/update_friend_list")
    public ResponseEntity<ErrorResponses> UpdateFriendRequest(@RequestParam String friendListID,@RequestParam String status){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponses = new ErrorResponses();
        try{
            friendListService.updateFriendList(friendListID,status,timeStamp);
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(200));
        }catch(Exception e){
            errorResponses.setCode("5001");
            errorResponses.setMessages(e.getLocalizedMessage());
            errorResponses.setDetail(e.getMessage());
            response = new ResponseEntity<>(errorResponses, HttpStatusCode.valueOf(500));
        }
        return response;
    }

}
