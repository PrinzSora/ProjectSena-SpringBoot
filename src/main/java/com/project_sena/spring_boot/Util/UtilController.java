package com.project_sena.spring_boot.Util;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Log4j2
@Controller
@RequestMapping("/util")
public class UtilController {
    private static final Logger logger = LogManager.getLogger(UtilController.class);
    private final UtilService utilService;

    public UtilController(UtilService utilService){
        this.utilService = utilService;
    }

    @GetMapping(value = "/poking")
    public ResponseEntity<ArrayList<String>> Poking(){
        ResponseEntity<ArrayList<String>> response;
        ArrayList<String> pokingList = new ArrayList<>();
        try{
            pokingList.add(utilService.FirstMethodInUtilService());
            response = new ResponseEntity<>(pokingList,HttpStatusCode.valueOf(200));
            logger.info("ehe");
        }catch (Exception e){
            response = new ResponseEntity<>(null,HttpStatusCode.valueOf(500));
        }
        return response;
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        //utilService.sendSimpleEmail("recipient@example.com", "Test Subject", "Test Email Body");
        return "Email sent successfully";
    }


}
