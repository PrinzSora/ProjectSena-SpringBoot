package com.project_sena.spring_boot.ProfileManagement.Service;

import com.project_sena.spring_boot.ProfileManagement.Entity.Profile;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.CreateProfileRequest;
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
    private void CreateProfile(CreateProfileRequest request)throws Exception{
        Profile profile = new Profile();
        profile.setID(new BigInteger(""));
        profile.setUID(0);
        profile.setBio("");
        profile.setGender("");
        profile.setLocation("");
        profile.setUpdatedDTM(LocalDateTime.now());
        profile.setCreatedDTM(LocalDateTime.now());
        profileRepo.save(profile);
    }

}
