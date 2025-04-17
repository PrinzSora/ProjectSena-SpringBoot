package com.project_sena.spring_boot.Gallery.Interface;


import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;

import java.time.LocalDateTime;

public interface ContentServiceInterface {

    public void getContent() throws Exception;
    public void addContent(ContentUploadRequest contentUploadRequest, LocalDateTime timeStamp) throws Exception;
    public void updateContent() throws Exception;
    public void delete() throws Exception;

}
