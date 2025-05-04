package com.project_sena.spring_boot.Gallery.Service;

import com.project_sena.spring_boot.Gallery.Entity.ContentEntity;
import com.project_sena.spring_boot.Gallery.Entity.MetaDataContentEntity;
import com.project_sena.spring_boot.Gallery.Interface.ContentServiceInterface;
import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;
import com.project_sena.spring_boot.Gallery.Repository.ContentRepo;
import com.project_sena.spring_boot.Gallery.Repository.MetaDataContentRepo;
import com.project_sena.spring_boot.Util.Constance.MediaType;
import com.project_sena.spring_boot.Util.Constance.UploadStatus;
import com.project_sena.spring_boot.Util.Service.FileSystemService;
import com.project_sena.spring_boot.Util.Service.ThreadLocalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class ImageContentService implements ContentServiceInterface {

    private final MetaDataContentRepo metaDataContentRepo;
    private final ContentRepo contentRepo;
    private final ThreadLocalService threadLocalService;
    private final FileSystemService systemService;

    public ImageContentService(MetaDataContentRepo metaDataContentRepo,
                               ContentRepo contentRepo,
                               ThreadLocalService threadLocalService, FileSystemService systemService) {
        this.metaDataContentRepo = metaDataContentRepo;
        this.contentRepo = contentRepo;
        this.threadLocalService = threadLocalService;
        this.systemService = systemService;
    }

    @Override
    public void getContent() {

    }

    @Override
    public void addContent(ContentUploadRequest contentUploadRequest, LocalDateTime timeStamp) throws Exception{

        Optional<MetaDataContentEntity> MetaDataContent = metaDataContentRepo.getMetaDataContentByCheckSum(contentUploadRequest.getCheckSum());
        if(MetaDataContent.isEmpty()){
            AddNewMetaDataContent(contentUploadRequest,timeStamp);
        }else {
            ContinueExistMetaDataContent(contentUploadRequest,MetaDataContent.get(),timeStamp);
        }
    }

    @Override
    public void updateContent() {
    }

    @Override
    public void delete() {
    }

    @Transactional
    private void AddNewMetaDataContent(ContentUploadRequest contentUploadRequest,LocalDateTime timeStamp) throws Exception{
        int uid = UUID.randomUUID().hashCode();
        MetaDataContentEntity metaDataContentEntity = new MetaDataContentEntity();
        metaDataContentEntity.setUID(uid);
        metaDataContentEntity.setDisplayName(contentUploadRequest.getDisplayName());
        metaDataContentEntity.setSize(contentUploadRequest.getSize());
        metaDataContentEntity.setCheckSum(contentUploadRequest.getCheckSum());
        metaDataContentEntity.setMediaType(MediaType.IMAGE);
        metaDataContentEntity.setCurrentChunk(contentUploadRequest.getCurrentChunk());
        metaDataContentEntity.setTotalChunk(contentUploadRequest.getTotalChunk());
        metaDataContentEntity.setUploadStatus(UploadStatus.NOTCOMPELED);
        metaDataContentEntity.setCompletedUploadDTM(null);
        metaDataContentEntity.setGroupID(null);
        metaDataContentEntity.setCreatedDTM(timeStamp);
        metaDataContentEntity.setUpdatedDTM(timeStamp);
        metaDataContentRepo.save(metaDataContentEntity);
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setContent_uid(uid);
        contentEntity.setCreatedDTM(timeStamp);
        contentEntity.setUpdatedDTM(timeStamp);
        contentEntity.setStatus("UPLOADING");
        contentRepo.save(contentEntity);
    }

    @Transactional
    private void ContinueExistMetaDataContent(ContentUploadRequest contentUploadRequest,MetaDataContentEntity metaDataContentEntity,LocalDateTime timeStamp)throws Exception{

        if(contentUploadRequest.getCurrentChunk() == metaDataContentEntity.getCurrentChunk()+1){
            metaDataContentEntity.setCurrentChunk(contentUploadRequest.getCurrentChunk());
            metaDataContentEntity.setUpdatedDTM(timeStamp);
        }else{
            throw new Exception();
        }

        if(metaDataContentEntity.getCurrentChunk() == metaDataContentEntity.getTotalChunk()){
            metaDataContentEntity.setCompletedUploadDTM(timeStamp);
            metaDataContentEntity.setUploadStatus(UploadStatus.COMPLETED);
        }
        metaDataContentRepo.save(metaDataContentEntity);

    }
}
