package com.project_sena.spring_boot.Gallery.Service;

import com.project_sena.spring_boot.Gallery.Interface.ContentServiceInterface;
import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VdoContentService implements ContentServiceInterface {
    @Override
    public void getContent() {
        System.out.println("VdoContentService");
    }

    @Override
    public void addContent(ContentUploadRequest bodyRequest, LocalDateTime timeStamp) {
        System.out.println("VdoContentService");
    }

    @Override
    public void updateContent() {

    }

    @Override
    public void delete() {

    }
}
