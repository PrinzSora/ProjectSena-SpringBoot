package com.project_sena.spring_boot.Gallery.Interface;


import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;

public interface ContentServiceInterface {

    public void getContent();
    public void addContent(ContentUploadRequest contentUploadRequest);
    public void updateContent();
    public void delete();

}
