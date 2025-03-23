package com.project_sena.spring_boot.ProfileManagement.Service;

import com.project_sena.spring_boot.ProfileManagement.Entity.Profile;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.CreateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.SearchProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.UpdateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.SearchProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Repository.ProfileRepo;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class ProfileServices {

    private final ProfileRepo profileRepo;
    private final UtilService utilService;

    public ProfileServices(ProfileRepo profileRepo, UtilService utilService){
        this.profileRepo = profileRepo;
        this.utilService = utilService;
    }

    @Transactional
     public void CreateProfile(CreateProfileRequest request,LocalDateTime timeStamp)throws Exception{
        Profile result = new Profile();
        result.setID(new BigInteger(""));
        result.setUID(0);
        result.setBio(request.getBio());
        result.setGender(request.getGender());
        result.setLocation(request.getLocation());
        result.setUpdatedDTM(timeStamp);
        result.setCreatedDTM(timeStamp);
        profileRepo.save(result);
    }

    @Transactional
    public void UpdateProfile(UpdateProfileRequest request,LocalDateTime timeStamp)throws  Exception{
        Profile result = new Profile();
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

}
