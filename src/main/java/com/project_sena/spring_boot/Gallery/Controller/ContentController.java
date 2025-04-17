package com.project_sena.spring_boot.Gallery.Controller;


import com.project_sena.spring_boot.Gallery.Interface.ContentServiceInterface;
import com.project_sena.spring_boot.Gallery.Model.Request.ContentUploadRequest;
import com.project_sena.spring_boot.Gallery.Repository.ContentRepo;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import com.project_sena.spring_boot.Util.Service.UtilService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


@RestController
@RequestMapping(value = "gallery/content")
public class ContentController {

    private final ContentRepo contentRepo;
    private final Map<String,ContentServiceInterface> contentServiceInterfaceMap;
    private final UtilService utilService;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    public ContentController(ContentRepo contentRepo,
                             Map<String,ContentServiceInterface> contentServiceInterfaceMap,
                             UtilService utilService){
        this.contentRepo = contentRepo;
        this.contentServiceInterfaceMap = contentServiceInterfaceMap;
        this.utilService = utilService;
    }

    @GetMapping("/get")
    public ResponseEntity<ErrorResponses> GetContent(@RequestHeader("Media-Type") String mediaType){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponse = new ErrorResponses();
        try{
            mediaType = utilService.convertMediaTypeInHTTPHeader(mediaType);
            ContentServiceInterface contentServiceInterface = this.contentServiceInterfaceMap.get(mediaType);
            contentServiceInterface.getContent();
            response = new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            errorResponse.setCode("5001");
            errorResponse.setDetail(e.getMessage());
            errorResponse.setMessages(e.getClass().getSimpleName());
            response = new ResponseEntity<>(errorResponse,HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<ErrorResponses> AddContent(@RequestHeader("Media-Type") String mediaType , @RequestBody ContentUploadRequest bodyRequest){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponse = new ErrorResponses();
        try{
            mediaType = utilService.convertMediaTypeInHTTPHeader(mediaType);
            ContentServiceInterface contentServiceInterface = this.contentServiceInterfaceMap.get(mediaType);
            contentServiceInterface.addContent(bodyRequest,timeStamp);
            response = new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            errorResponse.setCode("5001");
            errorResponse.setDetail(e.getMessage());
            errorResponse.setMessages(e.getClass().getSimpleName());
            response = new ResponseEntity<>(errorResponse,HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @PatchMapping("/update")
    public ResponseEntity<ErrorResponses> UpdateContent(@RequestHeader("Media-Type") String mediaType){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponse = new ErrorResponses();
        try{
            mediaType = utilService.convertMediaTypeInHTTPHeader(mediaType);
            ContentServiceInterface contentServiceInterface = this.contentServiceInterfaceMap.get(mediaType);
            contentServiceInterface.updateContent();
            response = new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            errorResponse.setCode("5001");
            errorResponse.setDetail(e.getMessage());
            errorResponse.setMessages(e.getClass().getSimpleName());
            response = new ResponseEntity<>(errorResponse,HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ErrorResponses> DeleteContent(@RequestHeader("Media-Type") String mediaType){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponse = new ErrorResponses();
        try{
            mediaType = utilService.convertMediaTypeInHTTPHeader(mediaType);
            ContentServiceInterface contentServiceInterface = this.contentServiceInterfaceMap.get(mediaType);
            contentServiceInterface.updateContent();
            response = new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(200));
        }catch (Exception e){
            errorResponse.setCode("5001");
            errorResponse.setDetail(e.getMessage());
            errorResponse.setMessages(e.getClass().getSimpleName());
            response = new ResponseEntity<>(errorResponse,HttpStatusCode.valueOf(500));
        }
        return response;
    }

}


