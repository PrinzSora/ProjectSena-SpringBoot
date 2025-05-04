package com.project_sena.spring_boot;

import com.project_sena.spring_boot.Util.Service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SystemChecker implements ApplicationRunner {

    @Autowired
    private FileSystemService fileSystemService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        fileSystemService.checkStorageInSystem();
        fileSystemService.intiStorage();
    }
}
