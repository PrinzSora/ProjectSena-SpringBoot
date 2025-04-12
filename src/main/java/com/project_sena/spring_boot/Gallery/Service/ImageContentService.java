package com.project_sena.spring_boot.Gallery.Service;

import com.project_sena.spring_boot.Gallery.Interface.ContentServiceInterface;
import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;
import org.springframework.stereotype.Service;


@Service
public class ImageContentService implements ContentServiceInterface {

    @Override
    public void getContent() {
        System.out.println("ImageContentService");
    }

    @Override
    public void addContent(ContentUploadRequest bodyRequest) {
        System.out.println("ImageContentService");
    }

    @Override
    public void updateContent() {

    }

    @Override
    public void delete() {

    }
}
