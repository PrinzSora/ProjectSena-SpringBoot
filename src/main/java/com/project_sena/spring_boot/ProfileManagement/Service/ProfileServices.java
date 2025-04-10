package com.project_sena.spring_boot.ProfileManagement.Service;

import com.project_sena.spring_boot.ProfileManagement.Entity.ProfileEntity;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.CreateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.SearchProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.UpdateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.ProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.SearchProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Repository.ProfileRepo;
import com.project_sena.spring_boot.Util.Constance.ProfileStatus;
import com.project_sena.spring_boot.Util.Service.ThreadLocalService;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileServices {

    private final ProfileRepo profileRepo;
    private final UtilService utilService;
    private final ThreadLocalService threadLocalService;

    public ProfileServices(ProfileRepo profileRepo, UtilService utilService, ThreadLocalService threadLocalService){
        this.profileRepo = profileRepo;
        this.utilService = utilService;
        this.threadLocalService = threadLocalService;
    }

    @Transactional
    public ProfileResponses GetProfile() throws Exception{
        Optional<ProfileEntity> profileEntity = profileRepo.getProfile(threadLocalService.getData().get("UID").toString());
        ProfileResponses result = new ProfileResponses();
        if(profileEntity.isEmpty()){
            throw new AccountNotFoundException();
        }
        if(Objects.equals(profileEntity.get().getStatus(),ProfileStatus.INACTIVE)){
            throw new AccountNotFoundException("INACTIVE");
        }
        if(Objects.equals(profileEntity.get().getStatus(),ProfileStatus.SUSPENDED)){
            throw new AccountNotFoundException("SUSPENDED");
        }
        if(Objects.equals(profileEntity.get().getStatus(),ProfileStatus.DELETED)){
            throw new AccountNotFoundException("DELETED");
        }
        if(Objects.equals(profileEntity.get().getStatus(),ProfileStatus.ACTIVE)){
            result.setUID(profileEntity.get().getUID());
            result.setPenName(profileEntity.get().getPenName());
            result.setGender(profileEntity.get().getGender());
            result.setBirthDate(profileEntity.get().getBirthDate().toString());
            result.setBio(profileEntity.get().getBio());
            result.setLocation(profileEntity.get().getLocation());
        }
        return result;
    }

    @Transactional
     public void CreateProfile(CreateProfileRequest request,LocalDateTime timeStamp)throws Exception{
        ProfileEntity result = new ProfileEntity();
        result.setUID(Integer.parseInt(threadLocalService.getData().get("UID").toString()));
        result.setPenName(request.getPenName());
        result.setStatus(ProfileStatus.ACTIVE);
        result.setBio(request.getBio());
        result.setGender(request.getGender());
        result.setLocation(request.getLocation());
        result.setBirthDate(request.getBirthDate());
        result.setUpdatedDTM(timeStamp);
        result.setCreatedDTM(timeStamp);
        profileRepo.save(result);
    }

    @Transactional
    public void UpdateProfile(UpdateProfileRequest request,LocalDateTime timeStamp)throws  Exception{
        ProfileEntity result = new ProfileEntity();
        result.setBio(request.getBio());
        result.setGender(request.getGender());
        result.setLocation(request.getLocation());
        result.setUpdatedDTM(timeStamp);
        profileRepo.save(result);
    }

    public SearchProfileResponses SearchProfile(SearchProfileRequest request)throws Exception{
        SearchProfileResponses result = new SearchProfileResponses();
        result.setProfilesList(null);
        return result;
    }

    public void DeleteProfile()throws Exception{
        profileRepo.deleteProfileByUID(threadLocalService.getData().get("UID").toString());
    }
}
