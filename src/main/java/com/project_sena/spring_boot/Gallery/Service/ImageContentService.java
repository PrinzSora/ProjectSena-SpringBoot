package com.project_sena.spring_boot.Gallery.Service;

import com.project_sena.spring_boot.Gallery.Entity.ContentEntity;
import com.project_sena.spring_boot.Gallery.Entity.MetaDataContentEntity;
import com.project_sena.spring_boot.Gallery.Interface.ContentServiceInterface;
import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;
import com.project_sena.spring_boot.Gallery.Repository.ContentRepo;
import com.project_sena.spring_boot.Gallery.Repository.MetaDataContentRepo;
import com.project_sena.spring_boot.Util.Constance.MediaType;
import com.project_sena.spring_boot.Util.Constance.UploadStatus;
import com.project_sena.spring_boot.Util.Service.ThreadLocalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class ImageContentService implements ContentServiceInterface {

    private final MetaDataContentRepo metaDataContentRepo;
    private final ContentRepo contentRepo;
    private final ThreadLocalService threadLocalService;

    public ImageContentService(MetaDataContentRepo metaDataContentRepo,
                               ContentRepo contentRepo,
                               ThreadLocalService threadLocalService) {
        this.metaDataContentRepo = metaDataContentRepo;
        this.contentRepo = contentRepo;
        this.threadLocalService = threadLocalService;
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
            //ContinueExistMetaDataContent(contentUploadRequest);
        }

    }

    @Override
    public void updateContent() {

    }

    @Override
    public void delete() {
    }

    private void AddNewMetaDataContent(ContentUploadRequest contentUploadRequest,LocalDateTime timeStamp) throws Exception{

        MetaDataContentEntity metaDataContentEntity = new MetaDataContentEntity();
        metaDataContentEntity.setUID(00);
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
        contentEntity.setContent_uid(00);
        contentEntity.setCreatedDTM(timeStamp);
        contentEntity.setUpdatedDTM(timeStamp);
        contentEntity.setStatus("UPLOADING");
        contentRepo.save(contentEntity);
    }

    private void ContinueExistMetaDataContent(MetaDataContentEntity metaDataContentEntity)throws Exception{
    }


}
