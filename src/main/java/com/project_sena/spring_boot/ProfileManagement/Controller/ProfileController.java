package com.project_sena.spring_boot.ProfileManagement.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/ProfileManagement")
public class ProfileController {

    @PostMapping
    public void CreateProfile(){

    }

}
