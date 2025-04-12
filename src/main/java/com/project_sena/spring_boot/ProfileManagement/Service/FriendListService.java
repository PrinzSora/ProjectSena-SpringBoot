package com.project_sena.spring_boot.ProfileManagement.Service;


import com.project_sena.spring_boot.ProfileManagement.Entity.FriendListEntity;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.FriendListResponses;
import com.project_sena.spring_boot.ProfileManagement.Repository.FriendListRepo;
import com.project_sena.spring_boot.ProfileManagement.Repository.ProfileRepo;
import com.project_sena.spring_boot.Util.Constance.FriendStatus;
import com.project_sena.spring_boot.Util.Service.ThreadLocalService;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FriendListService {

    private final FriendListRepo friendListRepo;
    private final ThreadLocalService threadLocalService;
    private final ProfileServices profileServices;
    private final UtilService utilService;

    public FriendListService(FriendListRepo friendListRepo,
                             ThreadLocalService threadLocalService,
                             ProfileServices profileServices,
                             UtilService utilService) {
        this.friendListRepo = friendListRepo;
        this.threadLocalService = threadLocalService;
        this.profileServices = profileServices;
        this.utilService = utilService;
    }

    public FriendListResponses getFriendList() throws Exception{
        List<FriendListEntity> queryResult;
        FriendListResponses result = new FriendListResponses();
        queryResult = friendListRepo.getFriendListByUID(threadLocalService.getData().get("UID").toString());
        if(!queryResult.isEmpty()){
            result.setFriendList(queryResult);
        }
        return result;
    }

    @Transactional
    public void sendFriendInvite(String friendUID,LocalDateTime timeStamp) throws  Exception{
        String userUID = threadLocalService.getData().get("UID").toString();
        if(Objects.equals(friendUID,threadLocalService.getData().get("UID").toString())){
            throw new AccountNotFoundException("R u lonely ?");
        };
        if(!profileServices.isProfileExist(Integer.parseInt(friendUID))){
            throw new AccountNotFoundException();
        };
        List<FriendListEntity> friendList = friendListRepo.getFriendListByUID(userUID);
        if(friendList.stream().anyMatch(
                each-> Objects.equals(each.getFriendUID(),Integer.parseInt(friendUID))
                                    || Objects.equals(each.getUserUID(),Integer.parseInt(friendUID)) )){
            throw new Exception("Your invite had sent");
        };
        FriendListEntity friendListEntity = new FriendListEntity();
        friendListEntity.setUserUID(Integer.parseInt(userUID));
        friendListEntity.setFriendUID(Integer.parseInt(friendUID));
        friendListEntity.setStatus(FriendStatus.PENDING);
        friendListEntity.setCreatedDTM(timeStamp);
        friendListEntity.setUpdatedDTM(timeStamp);
        friendListRepo.save(friendListEntity);
    }

    @Transactional
    public void updateFriendList(String friendListId,String status,LocalDateTime timeStamp) throws Exception{

        if(Objects.equals(friendListId,threadLocalService.getData().get("UID").toString())){
            throw new AccountNotFoundException();
        };
        FriendListEntity friendListEntity = friendListRepo.getReferenceById(new BigInteger(friendListId));
        if(!Objects.equals(utilService.isEnumValue(status,FriendStatus.class),null)){
            friendListEntity.setStatus(utilService.isEnumValue(status,FriendStatus.class));
        }
        friendListEntity.setUpdatedDTM(timeStamp);
        friendListRepo.save(friendListEntity);
    }
}
