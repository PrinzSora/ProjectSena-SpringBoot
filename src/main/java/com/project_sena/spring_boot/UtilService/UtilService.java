package com.project_sena.spring_boot.UtilService;


import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public String FirstMethodInUtilService(){
        String result;
        result = "Hello from UtilService";
        return result;
    }

}
