package com.project_sena.spring_boot.ProfileManagement.Service;

import com.project_sena.spring_boot.ProfileManagement.Entity.ProfileEntity;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.CreateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.SearchProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Request.UpdateProfileRequest;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.ProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Model.Responses.SearchProfileResponses;
import com.project_sena.spring_boot.ProfileManagement.Repository.ProfileRepo;
import com.project_sena.spring_boot.Util.Constance.Gender;
import com.project_sena.spring_boot.Util.Constance.ProfileStatus;
import com.project_sena.spring_boot.Util.Service.FileSystemService;
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
    private final FileSystemService fileSystemService;

    public ProfileServices(ProfileRepo profileRepo,
                           UtilService utilService,
                           ThreadLocalService threadLocalService,
                           FileSystemService fileSystemService){
        this.profileRepo = profileRepo;
        this.utilService = utilService;
        this.threadLocalService = threadLocalService;
        this.fileSystemService = fileSystemService;
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
        fileSystemService.makeDirectory(threadLocalService.getData().get("UID").toString());
        fileSystemService.genarateDefaultDirectory(threadLocalService.getData().get("UID").toString());
        profileRepo.save(result);
    }

    @Transactional
    public void UpdateProfile(UpdateProfileRequest request,LocalDateTime timeStamp)throws  Exception{
        Optional<ProfileEntity> result;
        result = profileRepo.getProfile(threadLocalService.getData().get("UID").toString());
        if(result.isEmpty()){
            throw new AccountNotFoundException();
        }
        if (request.getBio() != null && !request.getBio().isEmpty()) {
            result.get().setBio(request.getBio());
        }
        if (request.getGender() != null && !request.getGender().isEmpty()) {
            result.get().setGender(request.getGender());
        }
        if (request.getLocation() != null && !request.getLocation().isEmpty()) {
            result.get().setLocation(request.getLocation());
        }
        result.get().setUpdatedDTM(timeStamp);
        profileRepo.save(result.get());
    }

    //todo add more search option
    public SearchProfileResponses SearchProfile(SearchProfileRequest request)throws Exception{
        SearchProfileResponses result = new SearchProfileResponses();
        result.setProfilesList(null);
        return result;
    }

    public boolean isProfileExist(int UID) throws Exception {
        return profileRepo.existsByUID(UID);
    }

    public void DeleteProfile()throws Exception{
        profileRepo.deleteProfileByUID(threadLocalService.getData().get("UID").toString());
    }
}
