package com.project_sena.spring_boot.Gallery.Controller;


import com.project_sena.spring_boot.Gallery.Repository.ContentRepo;
import com.project_sena.spring_boot.Util.Model.ErrorResponses;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;


@RestController
@RequestMapping(value = "/content")
public class ContentController {

    private final ContentRepo contentRepo;

    public ContentController(ContentRepo contentRepo){
        this.contentRepo = contentRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<ErrorResponses> AddContent(){
        ResponseEntity<ErrorResponses> response;
        ErrorResponses errorResponse = new ErrorResponses();
        try{

            response = new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(200));
        }catch (Exception e){

            response = new ResponseEntity<>(errorResponse,HttpStatusCode.valueOf(500));
        }
        return response;
    }

}


